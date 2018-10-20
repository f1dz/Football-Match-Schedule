package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.detail.MatchDetailActivity
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.match_layout.*
import kotlinx.android.synthetic.main.match_layout.view.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class PrevMatchFragment: Fragment(), MatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.match_layout, container, false)

        adapter = MatchAdapter(rootView.context, matches) {
            startActivity<MatchDetailActivity>("match" to it)
        }
        rootView.match_rv.layoutManager = LinearLayoutManager(activity)
        rootView.match_rv.adapter = adapter

        presenter = MatchPresenter(this)
        presenter.getLastMatchList()

        rootView.swipe_refresh.onRefresh {
            presenter.getLastMatchList()
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
        adapter.notifyDataSetChanged()
    }
}