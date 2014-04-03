package karate.views

import kara.HtmlTemplateView
import karate.models.*
import kotlin.html.*
import kara.link
import kotlin.html.bootstrap.highlight


fun Index() = HtmlTemplateView<DefaultTemplate>(DefaultTemplate()) {
    content {

        h1 (id = "bigh1") { +"GiveAways Store" }

        DataStorage.db.withSession {
            h2 (id = "bigh1") { +"All items" }
            div (id = "div100") {
                div (id = "div20") { p { +"Item title" } }
                div (id = "div10") { p { +"Quantity" } }
                div (id = "div15") { p { +"Number to add/take" } }
                div (id = "div5") { p { +"Add" } }
                div (id = "div15") { p { +"Conference" } }
                div (id = "div25") { }

            }

            for (item in DataStorage.Items.selectAll().sortBy { it[DataStorage.Items.name] }) {

                div (id = "div100") {

                    form(null, item[DataStorage.Items.name]) {
                        action = "modify".link()
                        method = FormMethod.post

                        div  (id = "div20") {

                            input {
                                inputType = InputType.hidden
                                name = "item"
                                value = item[DataStorage.Items.name]

                                +"${item[DataStorage.Items.name]}"

                            }
                        }
                        div  (id = "div10") {

                            +DataStorage.getnum(DataStorage.db, item[DataStorage.Items.name]).toString()
                        }
                        div  (id = "div15") {

                            input {
                                inputType = InputType.text
                                name = "number"
                                value = ""
                                required = true
                                autocomplete = false

                            }
                        }


                        div  (id = "div5") {
                            button {
                                +"+"
                                name = "action"
                            }
                        }


                        div  (id = "div15") {

                            input {
                                inputType = InputType.text
                                name = "event"
                                value = ""

                                autocomplete = true

                            }
                        }
                        div  (id = "div5") {
                            input {
                                inputType = InputType.submit
                                value = "-"
                                name = "action"
                            }
                        }

                        div  (id = "div25") {
                            p { }

                        }

                    }
                }
            }
        }


        div  (id = "div100") {
            p {
                +"Add an item title"
            }
            form(null, "AddNewItemTitle") {
                action = "addtitle".link()
                method = FormMethod.post
                input {
                    inputType = InputType.text
                    name = "title"
                }
            }
            p { +" " }
        }


    }
}





