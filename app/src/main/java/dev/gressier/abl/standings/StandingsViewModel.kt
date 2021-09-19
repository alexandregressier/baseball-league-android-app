package dev.gressier.abl.standings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import dev.gressier.abl.data.BaseballDatabase
import dev.gressier.abl.data.BaseballRepository
import kotlinx.coroutines.launch

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

    fun refreshStandings() {
        viewModelScope.launch {
            repository.updateStandings()
        }
    }
}