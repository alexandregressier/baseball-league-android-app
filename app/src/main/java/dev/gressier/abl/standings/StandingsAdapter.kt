package dev.gressier.abl.standings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.gressier.abl.databinding.StandingsHeaderBinding
import dev.gressier.abl.databinding.StandingsTeamItemBinding
import kotlinx.coroutines.*

class StandingsAdapter : ListAdapter<StandingsListItem, RecyclerView.ViewHolder>(StandingsDiffCallback()) {

    fun addHeadersAndBuildStandings(uiTeamStanding: List<UITeamStanding>): Job =
        CoroutineScope(Dispatchers.Default).launch {
            withContext(Dispatchers.Main) {
                submitList(uiTeamStanding
                    .sortedWith(compareBy({ it.teamStanding.division }, { -it.teamStanding.wins }))
                    .groupBy { it.teamStanding.division }
                    .map { (division, teams) ->
                        listOf(division).map(StandingsListItem::Header) + teams.map(StandingsListItem::TeamItem)
                    }
                    .flatten()
                )
            }
        }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is StandingsListItem.Header -> STANDINGS_ITEM_VIEW_TYPE_HEADER
            is StandingsListItem.TeamItem -> STANDINGS_ITEM_VIEW_TYPE_TEAM
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            STANDINGS_ITEM_VIEW_TYPE_HEADER -> StandingsListHeaderViewHolder.from(parent)
            STANDINGS_ITEM_VIEW_TYPE_TEAM -> StandingsListTeamViewHolder.from(parent)
            else -> throw ClassCastException("Unknown view type `$viewType`")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StandingsListHeaderViewHolder ->
                (getItem(position) as? StandingsListItem.Header)?.let { holder.bind(it) }
            is StandingsListTeamViewHolder ->
                (getItem(position) as? StandingsListItem.TeamItem)?.let { holder.bind(it) }
        }
    }

    class StandingsListHeaderViewHolder(private val binding: StandingsHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(standingsHeaderItem: StandingsListItem.Header) {
            binding.divisionName = standingsHeaderItem.division.name
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = StandingsHeaderBinding.inflate(inflater, parent, false)

                return StandingsListHeaderViewHolder(binding)
            }
        }
    }

    class StandingsListTeamViewHolder(private val binding: StandingsTeamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(standingsTeamItem: StandingsListItem.TeamItem) {
            binding.uiTeamStanding = standingsTeamItem.uiTeamStanding
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = StandingsTeamItemBinding.inflate(inflater, parent, false)

                return StandingsListTeamViewHolder(binding)
            }
        }
    }

    companion object {
        private const val STANDINGS_ITEM_VIEW_TYPE_HEADER = 0
        private const val STANDINGS_ITEM_VIEW_TYPE_TEAM = 1
    }
}

private class StandingsDiffCallback : DiffUtil.ItemCallback<StandingsListItem>() {

    override fun areItemsTheSame(oldItem: StandingsListItem, newItem: StandingsListItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: StandingsListItem, newItem: StandingsListItem): Boolean =
        oldItem == newItem
}