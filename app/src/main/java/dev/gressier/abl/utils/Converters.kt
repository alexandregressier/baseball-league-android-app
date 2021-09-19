package dev.gressier.abl.utils

import dev.gressier.abl.api.models.ScheduledGameApiModel
import dev.gressier.abl.api.models.ScheduledGameBatterApiModel
import dev.gressier.abl.api.models.ScheduledGamePitcherApiModel
import dev.gressier.abl.api.models.TeamStandingApiModel
import dev.gressier.abl.api.models.enums.ScheduledGameStatusApiModel
import dev.gressier.abl.api.models.enums.WinLossApiModel
import dev.gressier.abl.scoreboard.ScheduledGame
import dev.gressier.abl.scoreboard.ScheduledGameStatus
import dev.gressier.abl.scoreboard.ScoreboardPlayerInfo
import dev.gressier.abl.standings.TeamStanding
import dev.gressier.abl.teams.Team

fun List<TeamStandingApiModel>.convertToTeamStandings(originalStandings: List<TeamStanding>): List<TeamStanding> =
    map { it.run {
        val originalStanding = originalStandings.firstOrNull { original -> original.teamId == it.teamId }
        TeamStanding(
            teamId = teamId,
            division = originalStanding?.division ?: Team.Division.UNKNOWN,
            wins = wins,
            losses = losses,
            winsLastTen = winsLastTen,
            streakCount = streakCount,
            streakType = streakType.toWinLoss(),
            divisionGamesBack = divisionGamesBack,
            leagueGamesBack = leagueGamesBack,
        ).apply { id = originalStanding?.id ?: 0 }
    } }

fun WinLossApiModel.toWinLoss() = when (this) {
    WinLossApiModel.Win -> TeamStanding.WinLoss.WIN
    WinLossApiModel.Loss -> TeamStanding.WinLoss.LOSS
    else -> TeamStanding.WinLoss.UNKNOWN
}

fun List<ScheduledGameApiModel>.convertToScheduledGames(): List<ScheduledGame> =
    map { it.run {
        val scoreboardPlayers = getScoreboardPlayerInfo()
        ScheduledGame(
            gameId = gameId,
            gameStatus = gameStatus.toScheduledGameStatus(),
            gameStartTime = gameStartTime,
            inning = inning,
            isTopOfInning = isTopOfInning,
            outs = outs,
            homeTeamId = homeTeam.teamId,
            homeWinLoss = "${homeTeam.wins}-${homeTeam.losses}",
            homeScore = homeTeam.score,
            awayTeamId = awayTeam.teamId,
            awayWinLoss = "${awayTeam.wins}-${awayTeam.losses}",
            awayScore = awayTeam.score,
            firstPlayer = scoreboardPlayers.getOrNull(0),
            secondPlayer = scoreboardPlayers.getOrNull(1),
            thirdPlayer = scoreboardPlayers.getOrNull(2),
        )
    } }

fun ScheduledGameStatusApiModel.toScheduledGameStatus() =
    when (this) {
        ScheduledGameStatusApiModel.Upcoming -> ScheduledGameStatus.UPCOMING
        ScheduledGameStatusApiModel.InProgress -> ScheduledGameStatus.IN_PROGRESS
        ScheduledGameStatusApiModel.Completed -> ScheduledGameStatus.COMPLETED
        else -> ScheduledGameStatus.UNKNOWN
    }

fun ScheduledGameApiModel.getScoreboardPlayerInfo(): List<ScoreboardPlayerInfo?> =
    when (gameStatus) {
        ScheduledGameStatusApiModel.Completed -> {
            val homeTeamWon = homeTeam.score > awayTeam.score
            listOf(
                (homeTeam.pitcherOfRecord.takeIf { homeTeamWon } ?: awayTeam.pitcherOfRecord)?.convertPitcherToScoreboardPlayerInfo(),
                (awayTeam.pitcherOfRecord.takeIf { homeTeamWon } ?: homeTeam.pitcherOfRecord)?.convertPitcherToScoreboardPlayerInfo(),
                (homeTeam.savingPitcher.takeIf { homeTeamWon } ?: awayTeam.savingPitcher)?.convertCloserToScoreboardPlayerInfo(),
            )
        }
        ScheduledGameStatusApiModel.Upcoming -> listOf(
            awayTeam.startingPitcher?.convertPitcherToScoreboardPlayerInfo(),
            homeTeam.startingPitcher?.convertPitcherToScoreboardPlayerInfo(),
        )
        ScheduledGameStatusApiModel.InProgress -> listOf(
            (homeTeam.currentPitcher.takeIf { isTopOfInning } ?: awayTeam.currentPitcher)?.convertPitcherToScoreboardPlayerInfo(),
            (awayTeam.currentBatter.takeIf { isTopOfInning } ?: homeTeam.currentBatter)?.convertCurrentBatterToScoreboardPlayerInfo(),
        )
        else -> emptyList()
    }

fun ScheduledGamePitcherApiModel.convertPitcherToScoreboardPlayerInfo() =
    ScoreboardPlayerInfo(playerName, "${wins}-${losses}, ${era}")

fun ScheduledGamePitcherApiModel.convertCloserToScoreboardPlayerInfo() =
    ScoreboardPlayerInfo(playerName, "$saves")

fun ScheduledGameBatterApiModel.convertCurrentBatterToScoreboardPlayerInfo() =
    ScoreboardPlayerInfo(playerName, "${hitsToday}-${atBatsToday}, ${battingAverage}")