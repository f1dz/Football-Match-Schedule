package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.Favorite
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.*
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.favorites_item.view.*

class MatchAdapter(private val ctx: Context, private var matches: List<Match>, private val favorites: List<Favorite>, private val listener: (Match) -> Unit): RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(LayoutInflater.from(ctx).inflate(R.layout.favorites_item, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val check = favorites.filter {
            it.matchId == matches[position].matchId.toString()
        }
        holder.bindItem(matches[position], check, listener)
    }
}

class MatchViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bindItem(match: Match, check: List<Favorite>, listener: (Match) -> Unit) {
        itemView.match_date.text = match.matchDate?.toLocalDate(match.matchTime!!)
        itemView.match_time.text = match.matchTime?.toLocalTime()
        itemView.home_team.text = match.homeTeam
        itemView.home_score.text = match.homeScore?.toString()
        itemView.away_team.text = match.awayTeam
        itemView.away_score.text = match.awayScore?.toString()

        val homeTeamBadge = match?.getHomeTeamDetail(itemView.context)
        val awayTeamBadge = match?.getAwayTeamDetail(itemView.context)

        if(homeTeamBadge.isNotEmpty())
            Picasso.get().load(homeTeamBadge).into(itemView.home_team_badge)

        if(awayTeamBadge.isNotEmpty())
            Picasso.get().load(awayTeamBadge).into(itemView.away_team_badge)

        if(check.size > 0)
            itemView.favorite.visible()
        else itemView.favorite.invisible()

        itemView.setOnClickListener { listener(match) }
    }

}
