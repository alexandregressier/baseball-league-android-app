package dev.gressier.abl.api.models

import dev.gressier.abl.api.models.enums.HandApiModel
import dev.gressier.abl.api.models.enums.HandApiModel.Right
import dev.gressier.abl.api.models.enums.PositionApiModel
import dev.gressier.abl.api.models.enums.PositionApiModel.Unknown

data class PlayerApiModel(
    val playerId: String,
    val teamId: String,
    val firstName: String,
    val lastName: String,
    val number: Int = 0,
    val bats: HandApiModel = Right,
    val throws: HandApiModel = Right,
    val position: PositionApiModel = Unknown,
    val boxScoreLastName: String?,
)