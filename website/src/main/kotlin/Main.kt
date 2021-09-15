import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.properties.TextDecoration
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
            css {
                marginTop = 7.vh
            }
            styledDiv {
                css {
                    width = 17.rem
                    margin = "auto"
                    textAlign = TextAlign.center
                }
                styledImg(src = "/img/team-logos/${id.lowercase()}.svg", alt = "$city $name Logo") {
                    css {
                        display = Display.inlineBlock
                        width = 14.rem
                        height = 14.rem
                        padding = "1.2rem"
                        margin = "auto"
                        backgroundColor = Color.white
                        borderRadius = 3.rem
                    }
                }
                p { +"$city $name" }
            }
        }
        styledDiv {
            css {
                margin = "10vw 0"
                textAlign = TextAlign.center
            }
            Link { attrs.to = Path.root
                styledSpan {
                    css {
                        display = Display.inlineBlock
                        width = 8.rem
                        padding = "1rem"
                        color = Color.black
                        textDecoration = TextDecoration.none
                        textAlign = TextAlign.center
                        fontWeight = FontWeight.bold
                        backgroundColor = Color.white
                        borderRadius = 1.rem
                    }
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
            css {
                width = 5.rem
                margin = "0.8rem"
                textAlign = TextAlign.center
            }
            styledImg(src = "/img/team-logos/${id.lowercase()}.svg", alt = "$city $name Logo") {
                css {
                    display = Display.inlineBlock
                    width = 4.rem
                    height = 4.rem
                    padding = "0.4rem"
                    margin = "auto"
                    backgroundColor = Color.white
                    borderRadius = 1.rem
                }
            }
            styledP {
                css {
                    fontSize = 0.7.rem
                    margin = "0 10px"
                }
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
        css {
            display = Display.flex
            justifyContent = JustifyContent.center
            flexWrap = FlexWrap.wrap
            maxWidth = 1000.px
            margin = "auto"
            paddingTop = 3.vh
        }
        teamRepository.getTeams().forEach { t ->
            Link { attrs.to = t.run { "${Path.teams}/$id?teamName=$city $name" }
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
            css {
                position = Position.sticky
                top = 0.px
                textAlign = TextAlign.center
                padding = "1rem"
                backgroundColor = Color.white
            }
            styledH1 {
                css {
                    margin = "0"
                    fontSize = 1.8.rem
                }
                Link { attrs.to = Path.root; +"Android Baseball League Teams" }
            }
        }
        Switch {
            Route { attrs { path = arrayOf("${Path.teams}/:teamId"); component = TeamDetails } }
            Redirect { attrs { from = Path.teams; to = Path.root } }
            Route { attrs { path = arrayOf(Path.root); component = TeamCards } }
        }
        styledFooter {
            css {
                padding = "1rem 0"
                color = Color.grey
                fontSize = 0.7.rem
                textAlign = TextAlign.center
            }
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