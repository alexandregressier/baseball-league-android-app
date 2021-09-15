import react.RBuilder
import react.router.dom.*
import styled.css
import styled.styledFooter
import styled.styledH1
import styled.styledHeader
import teams.TeamCards
import teams.TeamDetails
import teams.TeamRepository
import teams.TeamRepositoryStatic

val teamRepository: TeamRepository = TeamRepositoryStatic()

fun RBuilder.appWithRouter() {
    BrowserRouter {
        styledHeader {
            css { +Styles.header }
            styledH1 {
                css { +Styles.title }
                Link { attrs.to = Path.root; +"Android Baseball League Teams" }
            }
        }
        Switch {
            Route { attrs { path = arrayOf("${Path.teams}/:teamId"); component = TeamDetails } }
            Redirect { attrs { from = Path.teams; to = Path.root } }
            Route { attrs { path = arrayOf(Path.root); component = TeamCards } }
        }
        styledFooter {
            css { +Styles.footer }
            +"Copyright &copy; 2021"
        }
    }
}