package dev.gressier.abl.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.gressier.abl.standings.TeamStanding

@Dao
abstract class BaseballDao {

    @Insert
    abstract suspend fun insertStandings(standings: List<TeamStanding>)

    @Update
    abstract suspend fun updateStandings(standings: List<TeamStanding>)

    @Query("SELECT * FROM standings")
    abstract suspend fun getStandings(): List<TeamStanding>

    @Query("SELECT * FROM standings")
    abstract fun getLiveStandings(): LiveData<List<TeamStanding>>
}