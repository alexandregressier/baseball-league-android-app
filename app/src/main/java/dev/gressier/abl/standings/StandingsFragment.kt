package dev.gressier.abl.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.gressier.abl.databinding.FragmentStandingsBinding

class StandingsFragment : Fragment() {

    private val standingsViewModel by activityViewModels<StandingsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        FragmentStandingsBinding.inflate(inflater).apply {
            val standingsAdapter = StandingsAdapter()
            standingsList.adapter = standingsAdapter

            standingsSwipeRefreshLayout.setOnRefreshListener {
                standingsViewModel.refreshStandings()
            }
            standingsViewModel.standings.observe(viewLifecycleOwner) { standings ->
                standingsAdapter.addHeadersAndBuildStandings(standings)
                standingsSwipeRefreshLayout.isRefreshing = false
            }
            standingsViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
                if (!errorMessage.isNullOrEmpty())
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                standingsSwipeRefreshLayout.isRefreshing = false
            }
            standingsViewModel.refreshStandings()
        }.root
}