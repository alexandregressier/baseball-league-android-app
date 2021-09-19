package dev.gressier.abl.api.models

data class BoxScoreItemsApiModel(
    val batting: BatterBoxScoreItemApiModel? = null,
    val pitching: PitcherBoxScoreItemApiModel? = null,
)