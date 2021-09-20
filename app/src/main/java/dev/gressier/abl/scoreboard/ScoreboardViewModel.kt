package dev.gressier.abl.scoreboard

import android.app.Application
import androidx.lifecycle.*
import dev.gressier.abl.data.BaseballDatabase
import dev.gressier.abl.data.BaseballRepository
import dev.gressier.abl.teams.UITeam
import dev.gressier.abl.utils.orGetErrorMessage
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ScoreboardViewModel(application: Application) : AndroidViewModel(application) {

    private val currentDateTextFormat = DateTimeFormatter.ofPattern("EEEE, MMM d")
    private val selectedDate = MutableLiveData(LocalDate.now())

    private val repository: BaseballRepository

    val games: LiveData<List<ScheduledGame>>
    val teams: LiveData<List<UITeam>>
    val currentDateText: LiveData<String>
    val errorMessage = MutableLiveData("")

    init {
        repository = BaseballDatabase
            .getDatabase(application, viewModelScope)
            .baseballDao()
            .let(BaseballRepository.Companion::getInstance)

        games = Transformations.switchMap(selectedDate) { selectedDate ->
            refreshScores(selectedDate)
            repository.getGamesForDate(selectedDate)
        }
        teams = Transformations.map(games) { scheduledGames ->
            scheduledGames
                .flatMap { UITeam.fromTeamIds(it.homeTeamId, it.awayTeamId) }
                .filterNotNull()
        }
        currentDateText = Transformations.map(selectedDate, currentDateTextFormat::format)
    }

    fun goToDate(daysToMove: Long = 0, monthsToMove: Long? = null) {
        selectedDate.value?.apply {
            selectedDate.value =
                if (monthsToMove != null)
                    plusMonths(monthsToMove)
                else
                    plusDays(daysToMove)
        }
    }

    fun refreshScores(date: LocalDate) {
        viewModelScope.launch {
            repository.updateGamesForDate(date)
                .orGetErrorMessage(getApplication())
                ?.let { errorMessage.value = it }
        }
    }
}