package dev.gressier.abl.api.models

import dev.gressier.abl.api.models.enums.HandApiModel
import dev.gressier.abl.api.models.enums.HandApiModel.Right
import dev.gressier.abl.api.models.enums.PositionApiModel
import dev.gressier.abl.api.models.enums.PositionApiModel.Unknown
import dev.gressier.abl.api.typealiases.PlayerId
import dev.gressier.abl.api.typealiases.TeamId

data class PitcherBoxScoreItemApiModel(
    val playerId: PlayerId,
    val teamId: TeamId,
    val firstName: String,
    val lastName: String,
    val number: Int = 0,
    val bats: HandApiModel = Right,
    val throws: HandApiModel = Right,
    val position: PositionApiModel = Unknown,
    val boxScoreLastName: String?,
    val games: Int,
    val gamesStarted: Int,
    val outs: Int,
    val hits: Int,
    val doubles: Int,
    val triples: Int,
    val homeRuns: Int,
    val runs: Int,
    val earnedRuns: Int,
    val baseOnBalls: Int,
    val hitByPitches: Int,
    val strikeouts: Int,
    val errors: Int,
    val wildPitches: Int,
    val battersFaced: Int,
    val wins: Int,
    val losses: Int,
    val saves: Int,
    val blownSaves: Int,
    val holds: Int,
)