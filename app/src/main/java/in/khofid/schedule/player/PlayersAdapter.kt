package `in`.khofid.schedule.player

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Player
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_players_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class PlayersAdapter(private val ctx: Context, private val players: List<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.team_players_item, parent, false))

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(ctx, players[position], listener)
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){

    fun bindItem(ctx: Context, player: Player, listener: (Player) -> Unit) {

        Picasso.get().load(player.strCutout).placeholder(R.drawable.ic_player).into(itemView.player_cutout)
        itemView.player_name.text = player.strPlayer
        itemView.nationality.text = player.strNationality
        itemView.player_position.text = player.strPosition
        itemView.player_height_weight.text = ctx.getString(R.string.player_height_weight, player.strHeight, player.strWeight)

        itemView.onClick { listener(player) }
    }
}