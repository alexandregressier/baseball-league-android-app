package dev.gressier.abl.data

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.gressier.abl.api.typealiases.GameId
import dev.gressier.abl.scoreboard.ScheduledGame
import dev.gressier.abl.standings.TeamStanding

@Dao
abstract class BaseballDao {

    // Standings

    @Insert
    abstract suspend fun insertStandings(standings: List<TeamStanding>)

    @Update
    abstract suspend fun updateStandings(standings: List<TeamStanding>)

    @Query("SELECT * FROM standings")
    abstract suspend fun getStandings(): List<TeamStanding>

    @Query("SELECT * FROM standings")
    abstract fun getLiveStandings(): LiveData<List<TeamStanding>>

    // Games

    @Query("SELECT * FROM games WHERE gameId LIKE :dateString")
    abstract fun getGamesForDate(dateString: String): LiveData<List<ScheduledGame>>

    @Query("SELECT * FROM games WHERE gameId = :gameId")
    abstract fun getGameByGameId(gameId: GameId): ScheduledGame?

    @Insert
    abstract suspend fun insertGame(game: ScheduledGame)

    @Update
    abstract suspend fun updateGame(game: ScheduledGame)

    @Transaction
    open suspend fun insertOrUpdateGames(games: List<ScheduledGame>) {
        games.forEach { game ->
            getGameByGameId(game.gameId)?.let { dbGame ->
                updateGame(game.apply { id = dbGame.id })
            } ?: insertGame(game)
        }
    }
}