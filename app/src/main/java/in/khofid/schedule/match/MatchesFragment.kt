package `in`.khofid.schedule.match

import `in`.khofid.schedule.R
import `in`.khofid.schedule.search.SearchMatchActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.matches_container.view.*
import org.jetbrains.anko.support.v4.startActivity

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_button, menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                startActivity<SearchMatchActivity>()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}