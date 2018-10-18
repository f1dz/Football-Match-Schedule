package `in`.khofid.schedule.main

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {
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
            val response = apiRepository.doRequest(match)
            val data = gson.fromJson(
                response,
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}