package `in`.khofid.schedule.main

import `in`.khofid.schedule.model.Match

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
}