package dev.gressier.abl.standings

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.gressier.abl.standings.TeamStanding.WinLoss.LOSS
import dev.gressier.abl.standings.TeamStanding.WinLoss.WIN
import dev.gressier.abl.teams.Team
import dev.gressier.abl.teams.TeamId

@Entity(tableName = "standings")
data class TeamStanding(
    val teamId: TeamId,
    val division: Team.Division,
    val wins: Int,
    val losses: Int,
    val winsLastTen: Int,
    val streakCount: Int,
    val streakType: WinLoss,
    val divisionGamesBack: Double,
    val leagueGamesBack: Double,
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    enum class WinLoss {
        WIN, LOSS, UNKNOWN;
        val shortName = name.first()
    }

    companion object {
        val mockTeamStandings = listOf(
            Team.Appleton.run { TeamStanding(id, division, 80, 75, 6, 1, LOSS, 15.5, 15.5) },
            Team.Baraboo.run { TeamStanding(id, division, 78, 78, 5, 1, LOSS, 16.0, 18.0) },
            Team.EauClaire.run { TeamStanding(id, division, 73, 83, 4, 1, WIN, 21.0, 23.0) },
            Team.GreenBay.run { TeamStanding(id, division, 63, 93, 1, 7, LOSS, 33.0, 33.0) },
            Team.LaCrosse.run { TeamStanding(id, division, 65, 90, 6, 1, LOSS, 28.5, 30.5) },
            Team.LakeDelton.run { TeamStanding(id, division, 67, 89, 3, 3, LOSS, 27.0, 29.0) },
            Team.Milwaukee.run { TeamStanding(id, division, 91, 64, 6, 1, LOSS, 4.5, 4.5) },
            Team.Madison.run { TeamStanding(id, division, 94, 62, 5, 4, WIN, 0.0, 2.0) },
            Team.Pewaukee.run { TeamStanding(id, division, 93, 63, 4, 1, LOSS, 3.0, 3.0) },
            Team.SturgeonBay.run { TeamStanding(id, division, 75, 80, 7, 1, WIN, 20.5, 20.5) },
            Team.SpringGreen.run { TeamStanding(id, division, 55, 100, 5, 1, WIN, 38.5, 40.5) },
            Team.Shawano.run { TeamStanding(id, division, 72, 84, 6, 1, WIN, 24.0, 24.0) },
            Team.Waukesha.run { TeamStanding(id, division, 96, 60, 6, 3, WIN, 0.0, 0.0) },
            Team.WisconsinRapids.run { TeamStanding(id, division, 87, 68, 7, 1, WIN, 6.5, 8.5) },
        )
    }
}