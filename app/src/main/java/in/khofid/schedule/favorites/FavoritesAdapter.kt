package `in`.khofid.schedule.favorites

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.Favorite
import `in`.khofid.schedule.utils.toLocalDate
import `in`.khofid.schedule.utils.toLocalTime
import `in`.khofid.schedule.utils.toStringVoidNull
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_item.view.*

class FavoritesAdapter(private val ctx: Context, private var favorites: List<Favorite>, private val listener: (Favorite) -> Unit): RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        FavoritesAdapter.ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.match_item, parent, false))

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItem(fav: Favorite, listener: (Favorite) -> Unit) {
            itemView.match_date.text = fav.matchDate?.toLocalDate(fav.matchTime!!)
            itemView.match_time.text = fav.matchTime?.toLocalTime()
            itemView.home_team.text = fav.matchHomeTeam
            itemView.home_score.text = fav.matchHomeScore?.toStringVoidNull()
            Picasso.get().load(fav.matchHomeBadge).into(itemView.home_team_badge)
            itemView.away_team.text = fav.matchAwayTeam
            itemView.away_score.text = fav.matchAwayScore?.toStringVoidNull()
            Picasso.get().load(fav.matchAwayBadge).into(itemView.away_team_badge)

            itemView.setOnClickListener { listener(fav) }
        }

    }

}
