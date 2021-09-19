package dev.gressier.abl.api.models

import dev.gressier.abl.api.models.enums.DivisionApiModel
import dev.gressier.abl.api.typealiases.TeamId

data class TeamApiModel(
    val id: TeamId,
    val location: String,
    val nickname: String,
    val division: DivisionApiModel,
    val logo: String,
    val scheduleId: String = "",
)