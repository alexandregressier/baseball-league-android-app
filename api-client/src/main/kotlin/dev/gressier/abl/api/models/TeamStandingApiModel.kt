package dev.gressier.abl.api.models

import dev.gressier.abl.api.models.enums.WinLossApiModel
import dev.gressier.abl.api.typealiases.TeamId

data class TeamStandingApiModel(
    val teamId: TeamId,
    val wins: Int,
    val losses: Int,
    val winsLastTen: Int,
    val streakCount: Int,
    val streakType: WinLossApiModel,
    val divisionGamesBack: Double,
    val leagueGamesBack: Double,
)