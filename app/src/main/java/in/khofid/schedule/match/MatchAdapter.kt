package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.toLocalDate
import `in`.khofid.schedule.utils.toLocalTime
import `in`.khofid.schedule.utils.toSimpleDate
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.match_item.view.*

class MatchAdapter(private val ctx: Context, private var matches: List<Match>, private val listener: (Match) -> Unit): RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(LayoutInflater.from(ctx).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

class MatchViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bindItem(match: Match, listener: (Match) -> Unit) {
        itemView.match_date.text = match.matchDate?.toLocalDate(match.matchTime!!)
        itemView.match_time.text = match.matchTime?.toLocalTime()
        itemView.home_team.text = match.homeTeam
        itemView.home_score.text = match.homeScore?.toString()
        itemView.away_team.text = match.awayTeam
        itemView.away_score.text = match.awayScore?.toString()

        itemView.setOnClickListener { listener(match) }
    }
}
