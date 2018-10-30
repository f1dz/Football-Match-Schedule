package `in`.khofid.schedule.match

import `in`.khofid.schedule.model.League
import `in`.khofid.schedule.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun matchesNotFound()
    fun showMatchList(data: List<Match>)
    fun processBadge()
    fun fillSpinner(leagues: List<League>)
}