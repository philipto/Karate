package karate.views


import kara.*
import karate.styles.DefaultStyles
import kotlin.html.*
import kotlin.html.bootstrap.*


class DefaultTemplate : HtmlTemplate<DefaultTemplate, HTML>() {
    val content = Placeholder<BODY>()
    override fun HTML.render() {
        head {
            title("GiveAways Store")
            stylesheet(DefaultStyles)
            link("/public/bootstrap.css".link(), "stylesheet", "text/css")
            script("/public/bootstrap.js".link())
        }
        body {


                insert(content)
        }
    }
}
