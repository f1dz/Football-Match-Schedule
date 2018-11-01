package `in`.khofid.schedule.detail

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Player
import `in`.khofid.schedule.player.PlayerDetailActivity
import `in`.khofid.schedule.player.PlayersAdapter
import `in`.khofid.schedule.player.PlayersPresenter
import `in`.khofid.schedule.player.PlayersView
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.team_players_layout.view.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.support.v4.startActivity

class PlayersFragment: Fragment(), PlayersView {

    private lateinit var rootView: View
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var adapter: PlayersAdapter
    private lateinit var presenter: PlayersPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.team_players_layout, container, false)

        adapter = PlayersAdapter(rootView.context, players) {
            startActivity<PlayerDetailActivity>("player" to it)
        }
        rootView.players_rv.layoutManager = LinearLayoutManager(activity)
        rootView.players_rv.adapter = adapter

        val activity = getActivity() as TeamDetailActivity

        presenter = PlayersPresenter(this)
        presenter.getPlayersList(activity.getActivityTeam().idTeam.toString())

        return rootView
    }

    override fun showLoading() {
        rootView.progressbar.visible()
        rootView.players_not_found.invisible()
    }

    override fun hideLoading() {
        rootView.progressbar.invisible()
    }

    override fun showPlayersList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun playersNotFound() {
        rootView.progressbar.invisible()
        players.clear()
        adapter.notifyDataSetChanged()
        rootView.players_not_found.visible()
    }
}