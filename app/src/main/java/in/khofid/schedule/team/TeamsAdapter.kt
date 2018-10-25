package `in`.khofid.schedule.team

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Team
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamsAdapter(private val ctx: Context, private var teams: List<Team>, private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.team_item, parent, false))

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    fun updateList(data: List<Team>) {
        teams = data
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindItem(team: Team, listener: (Team) -> Unit) {
        Picasso.get().load(team.strTeamBadge).placeholder(R.drawable.ic_empty_badge).into(itemView.team_badge)
        itemView.team_name.text = team.strTeam
        itemView.formed_year.text = team.intFormedYear.toString()
        itemView.stadium.text = team.strStadium
        itemView.country.text = team.strCountry

        itemView.onClick { listener(team) }
    }
}