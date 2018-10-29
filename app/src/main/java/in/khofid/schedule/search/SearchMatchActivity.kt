package `in`.khofid.schedule.search

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteMatch
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.MatchDetailActivity
import `in`.khofid.schedule.match.MatchAdapter
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_match_search.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView, SearchView.OnQueryTextListener {

    private lateinit var menuItem: MenuItem
    private lateinit var searchView: SearchView
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var adapter: MatchAdapter
    private var matches: MutableList<Match> = mutableListOf()
    private var originMatches: MutableList<Match> = mutableListOf()
    private lateinit var favorites: List<FavoriteMatch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(getString(R.string.search_match))

        getFavorites()

        adapter = matchAdapter()
        presenter = SearchMatchPresenter(this)

        match_rv.layoutManager = LinearLayoutManager(this)
        match_rv.adapter = adapter
        progressbar.invisible()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        menuItem = menu.findItem(R.id.search)

        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun matchAdapter() =
        MatchAdapter(this, matches, favorites) {
            startActivity<MatchDetailActivity>("match" to it)
        }

    private fun getFavorites() {
        var fav: List<FavoriteMatch> = listOf()
        ctx.database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            fav = result.parseList(classParser())
        }
        favorites = fav
    }

    override fun showLoading() {
        progressbar.visible()
    }

    override fun hideLoading() {
        progressbar.invalidate()
    }

    override fun showMatchesList(data: List<Match>) {
        val mData = data.filter {
            it.sport == "Soccer"
        }
        matches.clear()
        matches.addAll(mData)
        originMatches.clear()
        originMatches.addAll(mData)
        adapter.notifyDataSetChanged()
        progressbar.invisible()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        if (query.length >= 3) {
            presenter.searchMatches(query)
            val data = originMatches.filter {
                (it.awayTeam!!.toLowerCase().contains(query) || it.homeTeam!!.toLowerCase().contains(query)) &&
                        it.sport == "Soccer"
            }
            matches.clear()
            matches.addAll(data)
            adapter.notifyDataSetChanged()
        } else {
            matches.clear()
            adapter.notifyDataSetChanged()
        }

        return true
    }

}