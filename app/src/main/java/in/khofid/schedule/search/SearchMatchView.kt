package `in`.khofid.schedule.search

import `in`.khofid.schedule.model.Match

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchesList(data: List<Match>)
}