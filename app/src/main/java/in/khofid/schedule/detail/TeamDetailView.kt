package `in`.khofid.schedule.detail

import `in`.khofid.schedule.model.Team

interface TeamDetailView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}