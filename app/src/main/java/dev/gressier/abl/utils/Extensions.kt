package dev.gressier.abl.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Double.round(places: Int): Double {
    var multiplier = 1.0
    repeat(places) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

fun Int.convertToInningsPitched(): Double =
    (this / 3) + (this % 3 / 10.0)

fun LocalDate.toGameDateString(): String =
    this.format(gameDateFormat)

private val gameDateFormat: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyyMMdd")