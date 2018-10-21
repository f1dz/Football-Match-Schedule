package `in`.khofid.schedule.detail

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.model.MatchDetailResponse
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val detailView: MatchDetailView) {
    fun getMatchDetail(matchId: Int){
        detailView.showLoading()
        doAsync {
            val data = Gson().fromJson(
                    ApiRepository().doRequest(TheSportDBApi.getMatchDetail(matchId)),
                    MatchDetailResponse::class.java
            )

            uiThread {
                detailView.hideLoading()
                detailView.showMatch(data.events)
            }
        }
    }

    fun getTeamDetail(match: MatchDetail){
        detailView.showLoading()
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
                detailView.hideLoading()
                val teams: ArrayList<Team> = arrayListOf(homeTeam.teams.first(),awayTeam.teams.first())
                detailView.showBadge(teams)
            }
        }

    }
}