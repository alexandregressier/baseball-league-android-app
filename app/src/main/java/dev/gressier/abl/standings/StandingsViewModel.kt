package dev.gressier.abl.standings

import android.app.Application
import androidx.lifecycle.*
import dev.gressier.abl.data.BaseballDatabase
import dev.gressier.abl.data.BaseballRepository
import dev.gressier.abl.utils.orGetErrorMessage
import kotlinx.coroutines.launch

class StandingsViewModel(application: Application): AndroidViewModel(application) {

    private val repository: BaseballRepository

    val standings: LiveData<List<UITeamStanding>>
    val errorMessage = MutableLiveData("")

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
                .orGetErrorMessage(getApplication())
                ?.let { errorMessage.value = it }
        }
    }
}