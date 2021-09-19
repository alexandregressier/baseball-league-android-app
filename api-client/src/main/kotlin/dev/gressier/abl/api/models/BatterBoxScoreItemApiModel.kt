package dev.gressier.abl.api.models

import dev.gressier.abl.api.models.enums.HandApiModel
import dev.gressier.abl.api.models.enums.HandApiModel.Right
import dev.gressier.abl.api.models.enums.PositionApiModel
import dev.gressier.abl.api.models.enums.PositionApiModel.Unknown
import dev.gressier.abl.api.typealiases.PlayerId
import dev.gressier.abl.api.typealiases.TeamId

data class BatterBoxScoreItemApiModel(
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
    val plateAppearances: Int,
    val atBats: Int,
    val runs: Int,
    val hits: Int,
    val doubles: Int,
    val triples: Int,
    val homeRuns: Int,
    val rbi: Int,
    val strikeouts: Int,
    val baseOnBalls: Int,
    val hitByPitch: Int,
    val stolenBases: Int,
    val caughtStealing: Int,
    val gidp: Int,
    val sacrificeHits: Int,
    val sacrificeFlies: Int,
    val errors: Int = 0,
    val passedBalls: Int = 0,
)