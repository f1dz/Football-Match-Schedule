package `in`.khofid.schedule.main

import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.model.Team

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(matches: List<MatchDetail>)
    fun showBadge(teams: ArrayList<Team>)
}