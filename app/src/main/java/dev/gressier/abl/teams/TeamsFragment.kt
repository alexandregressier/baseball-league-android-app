package dev.gressier.abl.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.*
import androidx.recyclerview.widget.RecyclerView
import dev.gressier.abl.R

class TeamsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? =
        inflater.inflate(R.layout.fragment_teams_grid, container, false).apply {
            if (this is RecyclerView) {
                layoutManager = GridLayoutManager(context, 2, VERTICAL, false)
                adapter = TeamsGridAdapter(UITeam.allTeams)
            }
        }
}