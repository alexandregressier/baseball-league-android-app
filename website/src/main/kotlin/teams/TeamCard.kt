package teams

import Styles
import react.Props
import react.RBuilder
import react.fc
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP

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