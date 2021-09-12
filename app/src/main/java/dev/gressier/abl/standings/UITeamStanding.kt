package dev.gressier.abl.standings

import dev.gressier.abl.teams.TeamId
import dev.gressier.abl.teams.UITeam

data class UITeamStanding(
    val teamStanding: TeamStanding,
    val teamName: String,
    val gamesBack: Double,
    val placeInLeague: Int,
    val placeInDivision: Int,
) {
    private val winPercentageNumber = teamStanding.run { wins.toDouble() / (wins + losses) }
    private val lastTenNumber = teamStanding.run { minOf(10 - winsLastTen, losses) }

    val teamId = teamStanding.teamId
    val wins = "${teamStanding.wins}"
    val losses = "${teamStanding.losses}"
    val winPercentage = "%.3f".format(winPercentageNumber)
    val gamesBackText = if (gamesBack <= 0) "-" else "$gamesBack"
    val lastTenText = "${teamStanding.winsLastTen}-${lastTenNumber}"
    val streakText = "${teamStanding.streakType.shortName}${teamStanding.streakCount}"
    val winLossText = "${teamStanding.wins} - ${teamStanding.losses}"

    companion object {

        fun fromTeamIdAndStandings(teamId: TeamId?, teamStandings: List<TeamStanding>): UITeamStanding? =
            UITeam.fromTeamId(teamId)?.let { fromTeamAndStandings(it, teamStandings) }

        fun fromTeamAndStandings(team: UITeam, teamStandings: List<TeamStanding>): UITeamStanding? =
            teamStandings
                .firstOrNull { it.teamId == team.teamId }
                ?.let { teamStanding ->
                    UITeamStanding(
                        teamStanding = teamStanding,
                        teamName = team.teamName,
                        gamesBack = teamStanding.divisionGamesBack,
                        placeInLeague = teamStandings
                            .sortedBy { it.leagueGamesBack }
                            .indexOf(teamStanding) + 1,
                        placeInDivision = teamStandings
                            .filter { it.division == team.division }
                            .sortedBy { it.divisionGamesBack }
                            .indexOf(teamStanding) + 1,
                    )
                }
    }
}