import kotlinx.browser.document
import react.Props
import react.RBuilder
import react.dom.*
import react.fc
import react.router.dom.*
import styled.injectGlobal

object UrlPrefix {
    const val team = "/teams"
}

val Teams = fc<Props> {
    div {
        h2 { +"Teams" }
        ul {
            li { Link { attrs.to = "${UrlPrefix.team}/abc"; +"ABC" } }
            li { Link { attrs.to = "${UrlPrefix.team}/def"; +"DEF" } }
            li { Link { attrs.to = "${UrlPrefix.team}/xyz"; +"XYZ" } }
            li { Link { attrs.to = UrlPrefix.team; +"None" } }
        }
    }
}
val Team = fc<Props> {
    val teamId = useParams()["teamId"] ?: return@fc
    h3 { +"Requested team ID: $teamId" }
}

fun RBuilder.appWithRouter() {
    BrowserRouter {
        h1 { Link { attrs.to = "/"; +"Android Baseball League Teams" } }
        Switch {
            Route { attrs { path = arrayOf("${UrlPrefix.team}/:teamId"); component = Team } }
            Redirect { attrs { from = UrlPrefix.team; to = "/" } }
            Route { attrs { path = arrayOf("/"); component = Teams } }
        }
    }
}

fun main() {
    injectGlobal(globalStyles)
    render(document.getElementById("root")) {
        appWithRouter()
    }
}