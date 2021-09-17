package teams

import Path
import Styles
import react.Props
import react.RBuilder
import react.dom.p
import react.fc
import react.router.dom.Link
import react.router.dom.useParams
import styled.css
import styled.styledDiv
import styled.styledImg
import teamRepository

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
                styledDiv {
                    css { +Styles.backButton }
                    +"< Back"
                }
            }
        }
    }
}

fun RBuilder.TeamDetails(handler: Props.() -> Unit) =
    child(TeamDetails) { attrs { handler() } }