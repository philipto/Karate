package karate.routes

import kara.*
import karate.models.DataStorage

// this is a router to route a request to appropriate class
// annotations correspond to URLs
// Kara looks for classes with the annotations via reflections

object Home {
    Get("/")
    class Index() : Request({ karate.views.Index() })

    Post("/modify")
    class ModifyQuantity(item: String, number: Int, action: String, event: String) : Request ({

        if ("-" == action)
           DataStorage.take(item, number, event)

        else
           DataStorage.add(item,number)

        karate.views.Index()   })

    Post("/addtitle")
    class AddItemTitle(title: String) : Request ({

        if ("title" == null)  {
            // do nothing
        }
        else
            DataStorage.addname(title)

        karate.views.Index()   })

    Get("/test")
    class Test() : Request({
        TextResult("This is a test action, yo")
    })

    Get("/json")
    class JsonPage : Request({
        json {
            jsonObject {
                jsonValue("version", 5)
                jsonObject("people")
                {
                    jsonValue("id", 1)
                    jsonValue("name", "Ilya")
                }
                jsonArray("cities")
                {
                    jsonValue("a")
                    jsonValue("b")
                    jsonValue("c")
                    jsonObject {
                        jsonValue("x", "y")
                    }
                }
            }
        }
    })

}
