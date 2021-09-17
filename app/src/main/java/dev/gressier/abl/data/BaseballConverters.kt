package dev.gressier.abl.data

import androidx.room.TypeConverter
import dev.gressier.abl.standings.TeamStanding
import dev.gressier.abl.teams.Team

class BaseballConverters {

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
}