package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.db.Favorite
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.MatchDetailActivity
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.model.TeamResponse
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.match_layout.*
import kotlinx.android.synthetic.main.match_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.uiThread

class NextMatchFragment: Fragment(), MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var rootView: View
    private lateinit var favorites: List<Favorite>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.match_layout, container, false)

        getFavorites()

        adapter = matchAdapter()
        rootView.match_rv.layoutManager = LinearLayoutManager(activity)
        rootView.match_rv.adapter = adapter

        presenter = MatchPresenter(this)
        presenter.getNextMatchList()

        rootView.swipe_refresh.onRefresh {
            getFavorites()
            rootView.match_rv.adapter = matchAdapter()
            presenter.getNextMatchList()
        }

        return rootView
    }

    override fun showLoading() {
        rootView.progressbar.visible()
    }

    override fun hideLoading() {
        rootView.progressbar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        swipe_refresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        presenter.processBadge(ctx, data)
        adapter.notifyDataSetChanged()
    }

    override fun processBadge() {
        adapter.notifyDataSetChanged()
    }

    private fun matchAdapter() =
        MatchAdapter(rootView.context, matches, favorites) {
            startActivity<MatchDetailActivity>("match" to it)
        }

    private fun getFavorites(){
        var fav: List<Favorite> = listOf()
        ctx.database.use {
            val result = select(Favorite.TABLE_FAVORITE)
            fav = result.parseList(classParser())
        }
        favorites = fav
    }
}