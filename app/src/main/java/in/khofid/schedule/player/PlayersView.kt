package `in`.khofid.schedule.player

import `in`.khofid.schedule.model.Player

interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayersList(data: List<Player>)
    fun playersNotFound()
}