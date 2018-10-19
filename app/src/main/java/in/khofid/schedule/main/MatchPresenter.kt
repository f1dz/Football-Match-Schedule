package `in`.khofid.schedule.main

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.*
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(private val view: MatchView) {
    fun getMatchDetail(matchId: Int){
        view.showLoading()
        doAsync {
            val data = Gson().fromJson(
                    ApiRepository().doRequest(TheSportDBApi.getMatchDetail(matchId)),
                    MatchDetailResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatch(data.events)
            }
        }
    }

    fun getTeamDetail(match: MatchDetail){
        doAsync {
            val homeTeam = Gson().fromJson(
                ApiRepository().doRequest(
                    TheSportDBApi.getTeamDetail(match.idHomeTeam!!)),
                TeamResponse::class.java
            )

            val awayTeam = Gson().fromJson(
                ApiRepository().doRequest(
                    TheSportDBApi.getTeamDetail(match.idAwayTeam!!)),
                TeamResponse::class.java
            )

            uiThread {
                var teams: ArrayList<Team> = ArrayList()
                teams.add(homeTeam.teams.first())
                teams.add(awayTeam.teams.first())
                view.showBadge(teams)
            }
        }

    }
}