package dev.gressier.abl.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import dev.gressier.abl.R

class StandingsFragment : Fragment() {

    private val standingsViewModel by activityViewModels<StandingsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? =
        inflater.inflate(R.layout.fragment_standings, container, false).apply {
            val standingsAdapter = StandingsAdapter()
            if (this is RecyclerView)
                adapter = standingsAdapter

            standingsViewModel.standings.observe(viewLifecycleOwner) { standings ->
                standingsAdapter.addHeadersAndBuildStandings(standings)
            }
        }
}