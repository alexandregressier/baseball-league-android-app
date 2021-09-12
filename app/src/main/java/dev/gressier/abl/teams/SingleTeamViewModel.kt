package dev.gressier.abl.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.gressier.abl.standings.TeamStanding
import dev.gressier.abl.standings.UITeamStanding

class SingleTeamViewModel : ViewModel() {

    val team = MutableLiveData<UITeam>()
    private val standings: LiveData<List<TeamStanding>>
    val teamStanding = MediatorLiveData<UITeamStanding>()

    init {
        standings = MutableLiveData<List<TeamStanding>>().apply {
            value = TeamStanding.mockTeamStandings
        }
        teamStanding.addSource(team) { updateUIStanding(it, standings.value) }
        teamStanding.addSource(standings) { updateUIStanding(team.value, it) }
    }

    fun setTeam(teamId: TeamId) {
        UITeam.allTeams.firstOrNull() { it.teamId == teamId }?.let { team.value = it }
    }

    private fun updateUIStanding(uiTeam: UITeam?, teamStandings: List<TeamStanding>?) {
        if (uiTeam != null && teamStandings != null)
            teamStanding.value = UITeamStanding.fromTeamAndStandings(uiTeam, teamStandings)
    }
}