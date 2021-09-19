package dev.gressier.abl.api.models

import dev.gressier.abl.api.typealiases.TeamId
import dev.gressier.abl.api.typealiases.UserId

data class AppSettingsApiModel(
    val userId: UserId,
    val favoriteTeamId: TeamId,
    val userTeamColorNavBar: Boolean,
    val startingLocation: String,
)