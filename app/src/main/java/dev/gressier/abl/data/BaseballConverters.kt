package dev.gressier.abl.data

import androidx.room.TypeConverter
import dev.gressier.abl.scoreboard.OccupiedBases
import dev.gressier.abl.scoreboard.ScheduledGameStatus
import dev.gressier.abl.standings.TeamStanding
import dev.gressier.abl.teams.Team
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BaseballConverters {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter fun fromDivision(division: Team.Division?) =
        (division ?: Team.Division.UNKNOWN).ordinal

    @TypeConverter fun toDivision(divisionOrdinal: Int?) =
        divisionOrdinal
            ?.let { Team.Division.values()[divisionOrdinal] }
            ?: Team.Division.UNKNOWN


    @TypeConverter fun fromWinLoss(winLoss: TeamStanding.WinLoss?) =
        (winLoss ?: TeamStanding.WinLoss.UNKNOWN).ordinal

    @TypeConverter fun toWinLoss(winLossOrdinal: Int?) =
        winLossOrdinal
            ?.let { TeamStanding.WinLoss.values()[winLossOrdinal] }
            ?: TeamStanding.WinLoss.UNKNOWN


    @TypeConverter fun fromLocalDateTime(date: LocalDateTime?): String? =
        date?.format(formatter)

    @TypeConverter fun toLocalDateTime(value: String?): LocalDateTime? =
        value?.let { formatter.parse(it, LocalDateTime::from) }


    @TypeConverter fun fromScheduledGameStatus(status: ScheduledGameStatus): Int =
        status.ordinal

    @TypeConverter fun toScheduledGameStatus(statusOrdinal: Int): ScheduledGameStatus =
        ScheduledGameStatus.values()[statusOrdinal]


    @TypeConverter fun fromOccupiedBases(bases: OccupiedBases?): String? =
        bases?.toStringList()

    @TypeConverter fun toOccupiedBases(basesStringList: String?): OccupiedBases? =
        OccupiedBases.fromStringList(basesStringList)
}