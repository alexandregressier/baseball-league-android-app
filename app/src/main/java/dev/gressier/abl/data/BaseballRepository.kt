package dev.gressier.abl.data

import androidx.lifecycle.LiveData
import dev.gressier.abl.standings.TeamStanding

class BaseballRepository(private val baseballDao: BaseballDao) {

    fun getStandings(): LiveData<List<TeamStanding>> =
        baseballDao.getLiveStandings()

    companion object {
        @Volatile private var instance: BaseballRepository? = null

        fun getInstance(baseballDao: BaseballDao): BaseballRepository =
            instance ?: synchronized(this) {
                instance ?: BaseballRepository(baseballDao)
                    .also { instance = it }
            }
    }
}