package karate.styles

import kotlin.html.*
import kara.*

object KarateStyles : Stylesheet() {

    override fun CssElement.render() {
        body {
            backgroundColor = c("#f0f0f0")
        }



        id("main") {
            width = 85.percent
            backgroundColor = c("#fff")
            margin = box(0.px, auto)
            padding = box(1.em)
            border = "1px solid #ccc"
            borderRadius = 5.px
        }

        id("button") {
            width = 40.px
            height = 40.px
            padding = box(1.em)
            margin = box(5.px)
            fontSize = 20.px
            color = Color.fromRgb(0, 255, 0)
            backgroundColor = Color.fromRgb(222, 222, 222)
        }

        forAny(input(att("type") equalTo "text"), textarea) {
            padding = box(4.px)
            width = 300.px
        }

        textarea {
            height = 80.px
        }

    }
}
