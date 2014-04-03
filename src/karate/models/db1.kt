package karate.models

import kotlin.sql.*
import kotlin.sql.Op


object DataStorage {
    var db = Database ("jdbc:postgresql://localhost:5432/invent",
            driver = "org.postrgesql.Driver", user = "rusin", password = "qazwsx")

    object Items: Table("items") {
        val id = serial("id")
        val name = varchar("name", 80)
        val quantity = integer ("quantity")
    }

        object Conferences: Table("conferences") {
            val conf_id = integer("conf_id")
            val title = varchar("title", 80)
        }

    object TakenItems: Table("takenitems") {
        val conf_id = integer("conf_id")
        val item_id = integer ("item_id")
        val item_num = integer ("items_num")
    }

    fun add (namei: String,
             quantityi: Int) {
        db.withSession {

            val itWas = Items.slice(Items.quantity).select(Items.name.like(namei)).toList()
            val cnt = count(Items.id)
            val r = Items.slice(cnt).select(Items.name.eq(namei)).toList()
            // select(count(items.id)) where items.name.like(name)

            val i = r[0][cnt]
            if ( i == 0 ) {
                Items.insert {
                    it[name] = namei
                    it[quantity] = quantityi
                }
            }

            else
                Items.update(Items.name.like(namei)) {
                    it[quantity] = quantityi + itWas[0][Items.quantity]
                }
            //                q = select(items.quantity) where items.name.like(namei)
            //                update (items.quantity(q + quantity)) where items.name.like(namei)
        }
    }


    fun take (namei: String,
              quantityi: Int,
              event: String) {
        db.withSession {

            val itWas = Items.slice(Items.quantity, Items.id).select(Items.name.like(namei)).toList()
            val cnt = count(Items.id)
            val r = Items.slice(cnt).select(Items.name.eq(namei)).toList()
            // select(count(items.id)) where items.name.like(name)

            val i = r[0][cnt]
            if ( i == 0 ) {
                // there are no items like requested
                System.out.println("no items like $namei")
            }
            else {
                val thereIs: Int = itWas[0][Items.quantity]
                val thereIsId : Int = itWas[0][Items.id]
                if ( quantityi > thereIs) {
                    System.out.println("there is just $thereIs of $namei")
                }
                else {

                    /* items found. Take them and record the transaction to Conferences */

                    Items.update(Items.name.like(namei)) {
                        it[quantity] = itWas[0][Items.quantity] - quantityi
                    }

                    /* find a conference */

                    if (event != null) {

                        val e1: List<ResultRow>
                        e1 = Conferences.slice(Conferences.conf_id, Conferences.title).select(Conferences.title.eq(event)).toList()

                    }
                    else
                        event.plus("Unknown")


                    var next : Int
                    val n1: List<ResultRow> = Conferences.slice(Conferences.conf_id).select(Conferences.conf_id.isNotNull()).toList()
                    val newList: List<Int> = n1.map { it[Conferences.conf_id] }
                    val n2 = newList.max()
//                    val n3 = n1.maxBy { it[Conferences.conf_id] }

                    if ( n2 == null)
                        next = 1
                    else
                        next = n2 + 1

                    @ToDo
                    // Check whether the conference is already in the Conferences table

                    Conferences.insert {
                        it[conf_id] = next
                        it[title] = event


                    }

                    TakenItems.insert {
                        it[conf_id] = next
                        it[item_id] = thereIsId
                        it[item_num] = quantityi
                    }

                }
            }
        }
    }

    fun getnum (db: Database, namei: String): Int {
        var q: Int
        q = 0
        db.withSession {
            val r = Items.slice(Items.quantity).select(Items.name.eq(namei)).toList()
            q = r[0][Items.quantity]
        }
        return q
    }

    fun addname (namei: String) {
        db.withSession {

            val cnt = count(Items.id)
            val r = Items.slice(cnt).select(Items.name.like(namei)).toList()
            // select(count(items.id)) where items.name.like(name)
            val i = r[0][cnt]
            if ( i == 0 ) {
                Items.insert {
                    it[name] = namei
                    it[quantity] = 0
                }
            }
            else {
                // error: an attempt to insert existing name
                System.out.println("this name exists already")
            }

        }
    }
}