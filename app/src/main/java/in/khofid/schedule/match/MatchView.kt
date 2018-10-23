package `in`.khofid.schedule.match

import `in`.khofid.schedule.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun processBadge()
}