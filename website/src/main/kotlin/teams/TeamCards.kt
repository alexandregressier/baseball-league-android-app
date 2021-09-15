package teams

import Path
import Styles
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.Link
import styled.css
import styled.styledDiv
import teamRepository

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