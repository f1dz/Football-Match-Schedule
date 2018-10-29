package `in`.khofid.schedule.favorites

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteTeam
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.TeamDetailActivity
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.team.TeamsAdapter
import `in`.khofid.schedule.utils.invisible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.favorites_team_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoritesTeamFragment: Fragment() {

    private var favoriteTeam: MutableList<Team> = mutableListOf()
    private lateinit var rootView: View
    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.favorites_team_layout, container, false)

        adapter = TeamsAdapter(rootView.context, favoriteTeam) {
            startActivity<TeamDetailActivity>("team" to it)
        }

        showFavorite()

        rootView.teams_rv.layoutManager = LinearLayoutManager(activity)
        rootView.teams_rv.adapter = adapter

        rootView.swipe_refresh.onRefresh { showFavorite() }

        return rootView
    }

    fun showFavorite() {
        context?.database?.use {
            rootView.swipe_refresh.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList((classParser<Team>()))
            favoriteTeam.clear()
            favoriteTeam.addAll(favorite)
            adapter.notifyDataSetChanged()
            rootView.progressbar.invisible()
        }
    }
}