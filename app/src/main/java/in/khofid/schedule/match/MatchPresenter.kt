package `in`.khofid.schedule.match

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView) {
    fun getLastMatchList(){
        view.showLoading()
        async(TheSportDBApi.getLastMatch())
    }

    fun getNextMatchList(){
        view.showLoading()
        async(TheSportDBApi.getNextMatch())
    }

    fun async(match: String){
        doAsync {
            val data = Gson().fromJson(
                ApiRepository().doRequest(match),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}