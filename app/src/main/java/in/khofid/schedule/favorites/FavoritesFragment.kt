package `in`.khofid.schedule.favorites

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.Favorite
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.MatchDetailActivity
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.invisible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.favorites_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoritesFragment: Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoritesAdapter
    private lateinit var rootView: View
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.favorites_layout, container, false)

        adapter = FavoritesAdapter(rootView.context, favorites) {
            val match = castFavoriteToMatch(it)
            startActivity<MatchDetailActivity>("match" to match)
        }

        progressBar = rootView.progressbar
        swipeRefresh = rootView.swipe_refresh
        showFavorite()

        rootView.favoritesRv.layoutManager = LinearLayoutManager(activity)
        rootView.favoritesRv.adapter = adapter

        swipeRefresh.onRefresh {
            showFavorite()
        }

        return rootView
    }

    fun showFavorite(){
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.clear()
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            progressBar.invisible()
        }
    }

    private fun castFavoriteToMatch(fav: Favorite): Match{
        return Match(
            fav.matchId?.toInt(),
            fav.matchDate,
            fav.matchTime,
            fav.matchHomeTeam,
            fav.matchAwayTeam,
            fav.matchHomeScore,
            fav.matchAwayScore)
    }
}