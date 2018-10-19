package `in`.khofid.schedule.main

import `in`.khofid.schedule.model.MatchDetail

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<MatchDetail>)
}