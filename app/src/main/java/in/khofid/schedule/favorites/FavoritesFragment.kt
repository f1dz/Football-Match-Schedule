package `in`.khofid.schedule.favorites

import `in`.khofid.schedule.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.favorites_container.view.*

class FavoritesFragment: Fragment() {

    private lateinit var tabAdapter: FavoritesTabAdapter
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.favorites_container, container, false)

        tabAdapter = FavoritesTabAdapter(rootView.context, activity!!.supportFragmentManager)
        rootView.favorites_view_pager.adapter = tabAdapter

        rootView.favorites_tab_layout.setupWithViewPager(rootView.favorites_view_pager)

        return rootView
    }

}