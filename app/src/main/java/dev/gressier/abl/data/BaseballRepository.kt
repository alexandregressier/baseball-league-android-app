package dev.gressier.abl.data

import androidx.lifecycle.LiveData
import dev.gressier.abl.api.services.getDefaultAblService
import dev.gressier.abl.standings.TeamStanding
import dev.gressier.abl.utils.convertToTeamStandings

class BaseballRepository(private val baseballDao: BaseballDao) {

    fun getStandings(): LiveData<List<TeamStanding>> =
        baseballDao.getLiveStandings()

    suspend fun updateStandings() {
        val standings = apiService.getStandings()
        if (standings.any()) {
            baseballDao.updateStandings(standings.convertToTeamStandings(baseballDao.getStandings()))
        }
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