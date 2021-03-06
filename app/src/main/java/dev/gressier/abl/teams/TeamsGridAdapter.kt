package dev.gressier.abl.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import dev.gressier.abl.NavGraphDirections
import dev.gressier.abl.R
import dev.gressier.abl.databinding.TeamsGridItemBinding

class TeamsGridAdapter(private val teams: List<UITeam>) : RecyclerView.Adapter<TeamsGridAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder =
        TeamViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.teams_grid_item, parent, false
        ))

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int = teams.size


    inner class TeamViewHolder(private val binding: TeamsGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UITeam) {
            binding.apply {
                team = item
                setClickListener {
                    it.findNavController().navigate(NavGraphDirections.actionGoToTeam(item.teamId, item.teamName))
                }
            }
        }
    }
}