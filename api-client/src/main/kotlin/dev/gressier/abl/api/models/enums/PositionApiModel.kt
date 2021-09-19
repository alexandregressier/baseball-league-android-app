package dev.gressier.abl.api.models.enums

enum class PositionApiModel(
    val shortName: String,
    val isOutfield: Boolean = false,
    val isPitcher: Boolean = false,
    val errorChance: Double = 98.6,
) {
    STARTING_PITCHER("SP", isPitcher = true, errorChance = 95.2),
    RELIEF_PITCHER("RP", isPitcher = true, errorChance = 95.2),
    CATCHER("C", errorChance = 99.4),
    FIRST_BASE("1B", errorChance = 99.4),
    SECOND_BASE("2B", errorChance = 98.2),
    THIRD_BASE("3B", errorChance = 95.8),
    SHORTSTOP("SS", errorChance = 97.2),
    LEFT_FIELD("LF", true),
    CENTER_FIELD("CF", true),
    RIGHT_FIELD("RF", true),
    DESIGNATED_HITTER("DH"),
    UNKNOWN("N/A"),
}