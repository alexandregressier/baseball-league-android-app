package teams

import kotlinx.css.Display
import kotlinx.css.GridTemplateColumns
import kotlinx.css.display
import kotlinx.css.gridTemplateColumns
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import styled.css
import styled.styledDiv

external interface TeamCardsListProps : Props {
    var teams: List<Team>
}

class TeamCardsList : RComponent<TeamCardsListProps, State>() {

    override fun RBuilder.render() {
        styledDiv {
            css {
                display = Display.grid
                gridTemplateColumns = GridTemplateColumns("repeat(auto-fill, minmax(6rem, 1fr))")
            }
            props.teams.forEach { teamCard { team = it } }
        }
    }
}

fun RBuilder.teamCardsList(handler: TeamCardsListProps.() -> Unit) =
    child(TeamCardsList::class) { attrs(handler) }