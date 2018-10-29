package `in`.khofid.schedule.player

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.PlayersResponse
import `in`.khofid.schedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayersPresenter(
    private val view: PlayersView,
    private val apiRepository: ApiRepository = ApiRepository(),
    private val gson: Gson = Gson(),
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayersList(teamId: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getPlayers(teamId)),
                    PlayersResponse::class.java
                )
            }
            view.showPlayersList(data.await().player)
            view.hideLoading()
        }
    }
}