package dev.gressier.abl.scoreboard

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "games")
data class ScheduledGame(
    val gameId: String,
    val gameStatus: ScheduledGameStatus,
    val gameStartTime: LocalDateTime,
    val inning: Int?,
    val isTopOfInning: Boolean?,
    val outs: Int? = 0,
    val homeTeamId: String,
    val homeWinLoss: String,
    val homeScore: Int? = 0,
    val awayTeamId: String,
    val awayWinLoss: String,
    val awayScore: Int? = 0,
    @Embedded(prefix = "first") val firstPlayer: ScoreboardPlayerInfo? = null,
    @Embedded(prefix = "second") val secondPlayer: ScoreboardPlayerInfo? = null,
    @Embedded(prefix = "third") val thirdPlayer: ScoreboardPlayerInfo? = null,
    val occupiedBases: OccupiedBases? = null,
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    companion object {
        val startTimeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")
    }
}

data class ScoreboardPlayerInfo(
    val playerName: String,
    val stats: String,
)

data class OccupiedBases(
    val first: Boolean = false,
    val second: Boolean = false,
    val third: Boolean = false,
) {
    fun toStringList() = listOf(first, second, third).joinToString(",", transform = Boolean::toString)

    companion object {
        fun fromStringList(stringList: String?) = stringList?.split(",")?.let { (first, second, third) ->
            OccupiedBases(first.toBoolean(), second.toBoolean(), third.toBoolean())
        }
    }
}

enum class ScheduledGameStatus {
    UPCOMING, IN_PROGRESS, COMPLETED, UNKNOWN
}