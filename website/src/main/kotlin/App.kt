import react.Props
import react.RBuilder
import react.RComponent
import react.State
import react.dom.h1
import teams.TeamRepository
import teams.TeamRepositoryStatic
import teams.teamCardsList

class App : RComponent<Props, State>() {

    private val teamRepository: TeamRepository = TeamRepositoryStatic()

    override fun RBuilder.render() {
        h1 { +"Android Baseball League Teams" }
        teamCardsList { teams = teamRepository.getTeams() }
    }
}

fun RBuilder.app(handler: Props.() -> Unit) =
    child(App::class) { attrs(handler) }