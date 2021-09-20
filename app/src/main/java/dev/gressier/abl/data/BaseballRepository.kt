package dev.gressier.abl.data

import androidx.lifecycle.LiveData
import dev.gressier.abl.api.services.getDefaultAblService
import dev.gressier.abl.data.BaseballRepository.ResultStatus.*
import dev.gressier.abl.scoreboard.ScheduledGame
import dev.gressier.abl.standings.TeamStanding
import dev.gressier.abl.utils.convertToScheduledGames
import dev.gressier.abl.utils.convertToTeamStandings
import dev.gressier.abl.utils.toGameDateString
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDate

class BaseballRepository(private val baseballDao: BaseballDao) {

    fun getStandings(): LiveData<List<TeamStanding>> =
        baseballDao.getLiveStandings()

    suspend fun updateStandings(): ResultStatus =
        safeApiRequest { apiService.getStandings() }.run {
            if (successful && result?.any() == true) {
                baseballDao.updateStandings(result.convertToTeamStandings(baseballDao.getStandings()))
                SUCCESS
            } else
                status
        }

    fun getGamesForDate(date: LocalDate): LiveData<List<ScheduledGame>> =
        baseballDao.getGamesForDate("${date.toGameDateString()}%")

    suspend fun updateGamesForDate(date: LocalDate): ResultStatus {
        val safeApiRequest = safeApiRequest { apiService.getGames(requestedDate = date) }
        return safeApiRequest.run {
            if (successful && result?.any() == true) {
                baseballDao.insertOrUpdateGames(result.convertToScheduledGames())
                SUCCESS
            } else
                status
        }
    }

    enum class ResultStatus {
        SUCCESS,
        NETWORK_EXCEPTION,
        REQUEST_EXCEPTION,
        GENERAL_EXCEPTION,
        UNKNOWN,
    }

    inner class ApiResult<T>(
        val result: T? = null,
        val status: ResultStatus = UNKNOWN,
    ) {
        val successful = status == SUCCESS
    }

    private suspend fun <T> safeApiRequest(apiFunction: suspend () -> T): ApiResult<T> =
        try {
            ApiResult(apiFunction(), SUCCESS)
        } catch (e: HttpException) {
            ApiResult(status = REQUEST_EXCEPTION)
        } catch (e: IOException) {
            ApiResult(status = NETWORK_EXCEPTION)
        } catch (e: Exception) {
            ApiResult(status = GENERAL_EXCEPTION)
        }

    companion object {
        private val apiService = getDefaultAblService()

        @Volatile private var instance: BaseballRepository? = null

        fun getInstance(baseballDao: BaseballDao): BaseballRepository =
            instance ?: synchronized(this) {
                instance ?: BaseballRepository(baseballDao)
                    .also { instance = it }
            }
    }
}