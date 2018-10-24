package `in`.khofid.schedule.detail

import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.model.Team

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(matches: List<MatchDetail>)
    fun showBadge(teams: ArrayList<Team>)
}