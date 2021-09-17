package dev.gressier.abl.standings

import android.app.Application
import androidx.lifecycle.*
import dev.gressier.abl.data.BaseballDatabase
import dev.gressier.abl.data.BaseballRepository

class StandingsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: BaseballRepository

    val standings: LiveData<List<UITeamStanding>>

    init {
        repository = BaseballDatabase
            .getDatabase(application, viewModelScope)
            .baseballDao()
            .let(BaseballRepository.Companion::getInstance)

        standings = Transformations.map(repository.getStandings()) { teamStandings ->
            teamStandings.mapNotNull { UITeamStanding.fromTeamIdAndStandings(it.teamId, teamStandings) }
        }
    }
}