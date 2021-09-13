import kotlinx.browser.document
import react.Props
import react.RBuilder
import react.dom.*
import react.fc
import react.router.dom.*
import styled.injectGlobal

val Home = fc<Props> { h2 { +"Home" } }
val Teams = fc<Props> {
    val match = useRouteMatch()

    div {
        h2 { +"Teams" }
        ul {
            li { Link { attrs.to = "${match.url}/abc"; +"ABC" } }
            li { Link { attrs.to = "${match.url}/def"; +"DEF" } }
            li { Link { attrs.to = "${match.url}/xyz"; +"XYZ" } }
        }
        Switch {
            Route { attrs { path = arrayOf("${match.path}/:teamId"); component = Team } }
            Route { attrs { path = arrayOf(match.path) }
                h3 { +"Please select a team." }
            }
        }
    }
}
val Team = fc<Props> {
    val teamId = useParams()["teamId"] ?: return@fc
    h3 { +"Requested team ID: $teamId" }
}

fun RBuilder.appWithRouter() {
    BrowserRouter {
        div {
            ul {
                li { Link { attrs.to = "/"; +"Home" } }
                li { Link { attrs.to = "/teams"; +"Teams" } }
            }
            Switch {
                Route { attrs { path = arrayOf("/teams"); component = Teams } }
                Route { attrs { path = arrayOf("/"); component = Home } }
            }
        }
    }
}

fun main() {
    injectGlobal(globalStyles)
    render(document.getElementById("root")) {
        appWithRouter()
    }
}