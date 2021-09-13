package teams

import kotlinx.css.*
import kotlinx.css.Color.Companion.white
import kotlinx.css.properties.BoxShadow
import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.p
import styled.css
import styled.styledA
import styled.styledDiv
import styled.styledImg

external interface TeamCardProps : Props {
    var team: Team
}

class TeamCard : RComponent<TeamCardProps, State>() {

    override fun RBuilder.render() {
        with(props.team) {
            styledDiv {
                styledA(href = "/teams/$id?teamName=$city $name") {
                    css {
                        fontSize = (0.8).rem
                    }
                    styledImg(src = "/img/team-logos/${id.lowercase()}.svg", alt = "$city $name Logo") {
                        css {
                            width = 3.rem
                            height = 3.rem
                            padding(1.rem)
                            borderRadius = 16.px
                            backgroundColor = white
                            boxShadow += BoxShadow(false, 0.px, 4.px, 8.px, 0.px, rgba(0, 0, 0, 0.2))
                        }
                    }
                    p { +"$city $name" }
                }
            }
        }
    }
}

fun RBuilder.teamCard(handler: TeamCardProps.() -> Unit) =
    child(TeamCard::class) { attrs(handler) }