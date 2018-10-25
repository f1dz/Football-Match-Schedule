package `in`.khofid.schedule.team

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.teams_container.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class TeamsFragment: Fragment(), TeamsView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var rootView: View
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.teams_container, container, false)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        rootView.league_spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(ctx, teams) {
            toast("Clicked ${it.strTeam}").show()
        }
        rootView.teams_rv.layoutManager = LinearLayoutManager(activity)
        rootView.teams_rv.adapter = adapter

        presenter = TeamsPresenter(this)
        rootView.league_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                presenter.getTeamsList(rootView.league_spinner.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        return rootView
    }

    override fun showLoading() {
        rootView.progressbar.visible()
    }

    override fun hideLoading() {
        rootView.progressbar.invisible()
    }

    override fun showTeamsList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}