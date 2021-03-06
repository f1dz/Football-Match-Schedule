package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteMatch
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.*
import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_item.view.*

class MatchAdapter(private val ctx: Context, private var matches: List<Match>, private val favorites: List<FavoriteMatch>, private val listener: (Match) -> Unit): RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder =
        MatchViewHolder(LayoutInflater.from(ctx).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val check = favorites.filter {
            it.matchId == matches[position].matchId.toString()
        }
        holder.bindItem(matches[position], check, listener)
    }
}

class MatchViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bindItem(match: Match, check: List<FavoriteMatch>, listener: (Match) -> Unit) {
        itemView.match_date.text = match.matchDate?.toLocalDate(match.matchTime)
        itemView.match_time.text = match.matchTime?.toLocalTime()
        itemView.home_team.text = match.homeTeam
        itemView.home_score.text = match.homeScore?.toString()
        itemView.away_team.text = match.awayTeam
        itemView.away_score.text = match.awayScore?.toString()

        match.dbGetTeam(itemView.context, match.homeTeamId!!).let {
            Picasso.get().load(it?.getBadge())
                .placeholder(R.drawable.ic_empty_badge)
                .into(itemView.home_team_badge)
        }

        match.dbGetTeam(itemView.context, match.awayTeamId!!).let {
            Picasso.get().load(it?.getBadge())
                .placeholder(R.drawable.ic_empty_badge)
                .into(itemView.away_team_badge)
        }

        if(match.matchDate?.toLocalDateTime(match.matchTime)!!.isPast())
            itemView.reminder.invisible()
        else itemView.reminder.visible()

        if(check.size > 0) itemView.favorite.visible()
        else itemView.favorite.invisible()

        itemView.setOnClickListener { listener(match) }
        itemView.reminder.setOnClickListener {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"
            intent.putExtra(CalendarContract.Events.TITLE, match.event)
            intent.putExtra(CalendarContract.Events.DESCRIPTION, match.fileName)
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, match.matchDate?.toLocalDateTime(match.matchTime)?.toMillis())
            intent.putExtra(CalendarContract.Events.ALL_DAY, false)
            itemView.context.startActivity(intent)
        }
    }

}
