package dev.gressier.abl.standings

import dev.gressier.abl.teams.Team
import dev.gressier.abl.teams.TeamId

sealed class StandingsListItem {

    abstract val id: TeamId

    data class TeamItem(val uiTeamStanding: UITeamStanding) : StandingsListItem() {
        override val id = uiTeamStanding.teamId
    }

    data class Header(val division: Team.Division) : StandingsListItem() {
        override val id = division.name
    }
}