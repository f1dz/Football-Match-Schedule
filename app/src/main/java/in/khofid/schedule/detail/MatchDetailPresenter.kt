package `in`.khofid.schedule.detail

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.MatchDetail
import `in`.khofid.schedule.model.MatchDetailResponse
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.model.TeamResponse
import `in`.khofid.schedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchDetailPresenter(
    private val detailView: MatchDetailView,
    private val apiRepository: ApiRepository = ApiRepository(),
    private val gson: Gson = Gson(),
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getMatchDetail(matchId: Int) {
        detailView.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getMatchDetail(matchId)),
                    MatchDetailResponse::class.java
                )
            }
            detailView.showMatch(data.await().events)
            detailView.hideLoading()
        }
    }

    fun getTeamDetail(match: MatchDetail) {
        detailView.showLoading()
        async(context.main) {
            val homeTeam = bg {
                gson.fromJson(
                    apiRepository.doRequest(
                        TheSportDBApi.getTeamDetail(match.idHomeTeam!!)
                    ),
                    TeamResponse::class.java
                )
            }

                val awayTeam = bg { gson.fromJson(
                    apiRepository.doRequest(
                        TheSportDBApi.getTeamDetail(match.idAwayTeam!!)
                    ),
                    TeamResponse::class.java
                )
            }
                detailView.hideLoading()
                val teams: ArrayList<Team> = arrayListOf(homeTeam.await().teams.first(), awayTeam.await().teams.first())
                detailView.showBadge(teams)
        }

    }
}