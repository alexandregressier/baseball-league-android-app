package dev.gressier.abl.api.models

import dev.gressier.abl.api.models.enums.HandApiModel
import dev.gressier.abl.api.models.enums.HandApiModel.RIGHT
import dev.gressier.abl.api.models.enums.PositionApiModel
import dev.gressier.abl.api.models.enums.PositionApiModel.UNKNOWN

data class PlayerApiModel(
    val playerId: String,
    val teamId: String,
    val firstName: String,
    val lastName: String,
    val number: Int = 0,
    val bats: HandApiModel = RIGHT,
    val throws: HandApiModel = RIGHT,
    val position: PositionApiModel = UNKNOWN,
    val boxScoreLastName: String?,
)