package `in`.khofid.schedule.team

import `in`.khofid.schedule.model.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamsList(data: List<Team>)
}