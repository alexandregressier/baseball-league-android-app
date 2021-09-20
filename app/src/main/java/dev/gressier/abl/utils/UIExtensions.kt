package dev.gressier.abl.utils

import android.app.Application
import dev.gressier.abl.R
import dev.gressier.abl.data.BaseballRepository
import dev.gressier.abl.data.BaseballRepository.ResultStatus.*
import dev.gressier.abl.scoreboard.ScheduledGame
import dev.gressier.abl.scoreboard.ScheduledGameStatus.*
import java.text.DecimalFormat

fun BaseballRepository.ResultStatus.orGetErrorMessage(application: Application): String? =
    when (this) {
        NETWORK_EXCEPTION -> application.resources.getString(R.string.network_exception_message)
        REQUEST_EXCEPTION -> application.resources.getString(R.string.request_exception_message)
        GENERAL_EXCEPTION -> application.resources.getString(R.string.general_exception_message)
        else -> null
    }

fun Int.withOrdinal(): String =
    "$this${
        if (this % 100 in 11..13) "th"
        else when (this % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }"

fun Double.toERAString(): String =
    DecimalFormat("0.00").format(this)

fun Double.toBattingPercentageString(): String =
    DecimalFormat("#.000").format(this)

val ScheduledGame?.inningText: String
    get() =
        if (this != null) "${if (isTopOfInning == true) "Top" else "Bot"} ${inning?.withOrdinal()}"
        else "N/A"

val ScheduledGame?.gameStartTimeText: String
    get() =
        if (this != null) ScheduledGame.startTimeFormat.format(gameStartTime)
        else "N/A"

fun ScheduledGame?.getPlayerLabel(playerNum: Int): String? =
    when (this?.gameStatus) {
        UPCOMING -> listOf(awayTeamId, homeTeamId, null)[playerNum]
        IN_PROGRESS -> listOf("P", "AB", null)[playerNum]
        COMPLETED -> listOf("W", "L", "S")[playerNum]
        else -> null
    }