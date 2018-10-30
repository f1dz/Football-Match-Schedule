package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteMatch
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.MatchDetailActivity
import `in`.khofid.schedule.model.League
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.Common
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.match_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class MatchesFragment: Fragment(), MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var rootView: View
    private lateinit var favorites: List<FavoriteMatch>
    private var leagueId: Int? = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.match_layout, container, false)

        getFavorites()

        // TODO polish spinner
        // TODO refactor xml style

        adapter = matchAdapter()
        rootView.match_rv.layoutManager = LinearLayoutManager(activity)
        rootView.match_rv.adapter = adapter

        presenter = MatchPresenter(this)
        presenter.fillLeagueSpinner()
        rootView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                matches.clear()
                leagueId = leagues.get(position).idLeague!!
                getMatchRequest()
            }

        }

        rootView.swipe_refresh.onRefresh {
            getFavorites()
            rootView.match_rv.adapter = matchAdapter()
            getMatchRequest()
        }

        return rootView
    }

    override fun showLoading() {
        rootView.progressbar.visible()
    }

    override fun hideLoading() {
        rootView.progressbar.invisible()
    }

    override fun matchesNotFound() {
        matches.clear()
        rootView.longSnackbar(R.string.matches_not_found)
        adapter.notifyDataSetChanged()
        rootView.progressbar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        rootView.swipe_refresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        presenter.processBadge(ctx, data)
        adapter.notifyDataSetChanged()
    }

    override fun processBadge() {
        adapter.notifyDataSetChanged()
    }

    override fun fillSpinner(data: List<League>) {
        leagues.addAll(data)

        rootView.spinner.adapter = Common.spinnerAdapter(rootView.context, data)
    }

    private fun matchAdapter() =
        MatchAdapter(rootView.context, matches, favorites) {
            startActivity<MatchDetailActivity>("match" to it)
        }

    private fun getFavorites(){
        var fav: List<FavoriteMatch> = listOf()
        rootView.context.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            fav = result.parseList(classParser())
        }
        favorites = fav
    }

    fun getMatchRequest(){
        if (arguments != null && arguments!!["tabIndex"] == 0)
            presenter.getNextMatchList(leagueId.toString())
        else
            presenter.getLastMatchList(leagueId.toString())

    }
}