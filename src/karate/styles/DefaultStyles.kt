package karate.styles

import kotlin.html.*
import kara.*


/** The default stylesheet for the demo application.
 */
Get("/dynamic/default.css")


object DefaultStyles : Stylesheet() {

    override fun CssElement.render() {
        body {
            backgroundColor = c("#f0f0f0")
            fontSize = 26.px
        }

        p {
            fontSize = 20.px
        }

        id("bigh1") {
            fontSize = 36.px
            margin = box(16.px)
        }

        id("bigh2") {
            margin = box(16.px)
            fontSize = 32.px

        }

        id("main") {
            width = 85.percent
            backgroundColor = c("#fff")
            padding = box(1.em)
            border = "1px solid #ccc"
            borderRadius = 5.px
        }

        id ("div20") {
            width = 20.percent
            display = display.inline
            float = float.left
            fontSize = 18.px

        }

        id ("div15") {
            width = 15.percent
            display = display.inline
            float = float.left
        }

        id ("div10") {
            width = 10.percent
            display = display.inline
            float = float.left
            fontSize = 18.px

        }

        id ("div5") {
            width = 5.percent
            display = display.inline
            float = float.left
            fontSize = 18.px
            paddingLeft = 18.px

        }

        id ("div50") {
            width = 50.percent
            display = display.inline
            float = float.left
            fontSize = 18.px

        }


        id ("div40") {
            width = 40.percent
            display = display.inline
            float = float.left
            fontSize = 18.px
        }

        id ("div100") {
            width = 99.percent
            display = display.inline
            float = float.left
            fontSize = 18.px
            margin = box(1.px,1.px,1.px,20.px)
        }

        id ("div25") {
            width = 25.percent
            display = display.inline
            float = float.left
            fontSize = 18.px

        }

        forAny(input(att("type") equalTo "text"), textarea) {
            padding = box(2.px)
            width = 250.px
        }

        textarea {
            height = 80.px
        }

    }
}
