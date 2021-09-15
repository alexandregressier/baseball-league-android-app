import kotlinx.browser.document
import react.Props
import react.RBuilder
import react.dom.p
import react.dom.render
import react.fc
import react.router.dom.*
import styled.*
import teams.Team
import teams.TeamRepository
import teams.TeamRepositoryStatic

object Path {
    const val teams = "/teams"
    const val root = "/"
}

val teamRepository: TeamRepository = TeamRepositoryStatic()

val TeamDetails = fc<Props> {
    val teamId = useParams()["teamId"] ?: return@fc
    val team = teamRepository.getTeamById(teamId)

    team?.apply {
        styledDiv {
            css { +Styles.bigCardContainer }
            styledDiv {
                css { +Styles.bigCard }
                styledImg(src = "/images/team-logos/${id.lowercase()}.svg", alt = "$city $name Logo") {
                    css { +Styles.bigCardImage }
                }
                p { +"$city $name" }
            }
        }
        styledDiv {
            css { +Styles.actionButtonsContainer }
            Link {
                attrs.to = Path.root
                styledSpan {
                    css { +Styles.backButton }
                    +"< Back"
                }
            }
        }
    }
}

fun RBuilder.TeamDetails(handler: Props.() -> Unit) =
    child(TeamDetails) { attrs { handler() } }


external interface TeamCardProps : Props {
    var team: Team
}

val TeamCard = fc<TeamCardProps> { props ->
    props.team.apply {
        styledDiv {
            css { +Styles.card }
            styledImg(src = "/images/team-logos/${id.lowercase()}.svg", alt = "$city $name Logo") {
                css { +Styles.cardImage }
            }
            styledP {
                css { +Styles.cardLabel };
                +"$city $name"
            }
        }
    }
}

fun RBuilder.TeamCard(handler: TeamCardProps.() -> Unit) =
    child(TeamCard) { attrs { handler() } }


external interface TeamCardsProps : Props {
    var teams: List<Team>
}

val TeamCards = fc<TeamCardsProps> { props ->
    styledDiv {
        css { +Styles.cardsContainer }
        teamRepository.getTeams().forEach { t ->
            Link {
                attrs.to = t.run { "${Path.teams}/$id?teamName=$city $name" }
                TeamCard { team = t }
            }
        }
    }
}

fun RBuilder.TeamCards(handler: TeamCardsProps.() -> Unit) =
    child(TeamCards) { attrs { handler() } }


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

fun main() {
    injectGlobal(globalStyles)
    render(document.getElementById("root")) {
        appWithRouter()
    }
}