package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteMatch
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.MatchDetailActivity
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.Common
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.SearchView
import kotlinx.android.synthetic.main.match_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class PrevMatchFragment: Fragment(), MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private var originMatches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var rootView: View
    private lateinit var favorites: List<FavoriteMatch>
    private var leagueId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.match_layout, container, false)

        val spinnerLeagueId = ctx.resources.getIntArray(R.array.idLeague)
        rootView.spinner.adapter = Common.spinnerAdapter(rootView.context)

        getFavorites()

        adapter = matchAdapter()
        rootView.match_rv.layoutManager = LinearLayoutManager(activity)
        rootView.match_rv.adapter = adapter

        presenter = MatchPresenter(this)
        rootView.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>) {}

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                matches.clear()
                leagueId = spinnerLeagueId.get(position)
                presenter.getLastMatchList(leagueId.toString())
            }

        }

        rootView.swipe_refresh.onRefresh {
            getFavorites()
            rootView.match_rv.adapter = matchAdapter()
            presenter.getLastMatchList(leagueId.toString())
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
        rootView.swipe_refresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        originMatches.clear()
        originMatches.addAll(data)
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
        var fav: List<FavoriteMatch> = listOf()
        ctx.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            fav = result.parseList(classParser())
        }
        favorites = fav
    }

    /*override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
//        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        var input = query.toLowerCase()
        var data = originMatches.filter {
            it.homeTeam!!.toLowerCase().contains(input) || it.awayTeam!!.toLowerCase().contains(input)
        }
        matches.clear()
        matches.addAll(data)
        presenter.processBadge(ctx, data)
        adapter.notifyDataSetChanged()

        return true
    }
    */
}