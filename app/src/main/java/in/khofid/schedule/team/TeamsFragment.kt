package `in`.khofid.schedule.team

import `in`.khofid.schedule.R
import `in`.khofid.schedule.db.FavoriteTeam
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.detail.TeamDetailActivity
import `in`.khofid.schedule.model.League
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.utils.Common
import `in`.khofid.schedule.utils.invisible
import `in`.khofid.schedule.utils.visible
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.SearchView
import kotlinx.android.synthetic.main.teams_container.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class TeamsFragment: Fragment(), TeamsView, SearchView.OnQueryTextListener {

    private var teams: MutableList<Team> = mutableListOf()
    private var originTeams: MutableList<Team> = mutableListOf()
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var rootView: View
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter
    private lateinit var searchView: SearchView
    private lateinit var favorites: List<FavoriteTeam>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.teams_container, container, false)

        getFavorites()

        adapter = TeamsAdapter(ctx, teams, favorites) {
            startActivity<TeamDetailActivity>("team" to it)
        }

        rootView.teams_rv.layoutManager = LinearLayoutManager(activity)
        rootView.teams_rv.adapter = adapter

        presenter = TeamsPresenter(this)
        presenter.fillLeagueSpinner()
        rootView.league_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                teams.clear()
                presenter.getTeamsList(leagues.get(position).strLeague)
                searchView.setQuery("", false)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        return rootView
    }

    private fun getFavorites() {
        rootView.context.database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            favorites = result.parseList(classParser())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun fillSpinner(data: List<League>) {
        leagues.addAll(data)

        rootView.league_spinner.adapter = Common.spinnerAdapter(rootView.context, data)
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
        originTeams.clear()
        originTeams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun teamsNotFound() {
        teams.clear()
        rootView.longSnackbar(R.string.teams_not_found)
        adapter.notifyDataSetChanged()
        rootView.progressbar.invisible()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(query: String): Boolean {
        var input = query.toLowerCase()
        var data = originTeams.filter { it.strTeam!!.toLowerCase().contains(input) }
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()

        return true
    }

}