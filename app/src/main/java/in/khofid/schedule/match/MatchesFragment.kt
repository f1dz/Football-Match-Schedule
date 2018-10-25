package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.matches_container.view.*

class MatchesFragment: Fragment() {

    private lateinit var tabAdapter: TabAdapter
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.matches_container, container, false)

        tabAdapter = TabAdapter(rootView.context, activity!!.supportFragmentManager)
        rootView.match_view_pager.adapter = tabAdapter

        rootView.match_tab_layout.setupWithViewPager(rootView.match_view_pager)

        return rootView
    }
}