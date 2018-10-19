package `in`.khofid.schedule.main

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.MatchDetailResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView) {
    fun getMatchDetail(matchId: Int){
        view.showLoading()
        doAsync {
            val match = ApiRepository().doRequest(
                TheSportDBApi.getMatchDetail(matchId))
            var gson = Gson()
            val data = gson.fromJson(
                    match,
                    MatchDetailResponse::class.java
            )


            uiThread {
                view.hideLoading()
                view.showMatch(data.events)
            }
        }
    }
}