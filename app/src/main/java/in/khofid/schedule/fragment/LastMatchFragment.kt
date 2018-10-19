package `in`.khofid.schedule.fragment

import `in`.khofid.schedule.R
import `in`.khofid.schedule.main.MainAdapter
import `in`.khofid.schedule.main.MainPresenter
import `in`.khofid.schedule.main.MainView
import `in`.khofid.schedule.main.MatchDetailActivity
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.match_layout.*
import kotlinx.android.synthetic.main.match_layout.view.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class LastMatchFragment: Fragment(), MainView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var adapter: MainAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.match_layout, container, false)

        adapter = MainAdapter(rootView.context, matches) {
            startActivity<MatchDetailActivity>("match" to it)
        }
        rootView.match_rv.layoutManager = LinearLayoutManager(activity)
        rootView.match_rv.adapter = adapter

        presenter = MainPresenter(this)
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