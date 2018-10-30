package `in`.khofid.schedule.match

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.LeaguesResponse
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.MatchResponse
import `in`.khofid.schedule.model.TeamResponse
import `in`.khofid.schedule.utils.CoroutineContextProvider
import `in`.khofid.schedule.utils.dbGetTeam
import `in`.khofid.schedule.utils.insertToDb
import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository = ApiRepository(),
    private val gson: Gson = Gson(),
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLastMatchList(leagueId: String) {
        view.showLoading()
        async(TheSportDBApi.getLastMatch(leagueId))
    }

    fun getNextMatchList(leagueId: String) {
        view.showLoading()
        async(TheSportDBApi.getNextMatch(leagueId))
    }

    fun async(match: String) {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(match),
                    MatchResponse::class.java
                )
            }
            val matches = data.await().events
            if(matches == null)
                view.matchesNotFound()
            else {
                view.showMatchList(matches)
                view.hideLoading()
            }
        }
    }

    fun processBadge(ctx: Context, match: List<Match>) {
        async(context.main) {
            match.forEach {
                bg {
                    if (it.dbGetTeam(ctx, it.homeTeamId!!) == null) {
                        val response = Gson().fromJson(
                            ApiRepository().doRequest(
                                TheSportDBApi.getTeamDetail(it.homeTeamId!!)
                            ),
                            TeamResponse::class.java
                        )

                        response.teams.first().insertToDb(ctx)
                    }

                    if (it.dbGetTeam(ctx, it.awayTeamId!!) == null) {
                        val response = Gson().fromJson(
                            ApiRepository().doRequest(
                                TheSportDBApi.getTeamDetail(it.homeTeamId!!)
                            ),
                            TeamResponse::class.java
                        )

                        response.teams.first().insertToDb(ctx)
                    }
                }
            }
                view.processBadge()
        }
    }

    fun fillLeagueSpinner() {
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(
                        TheSportDBApi.getLeagues()),
                    LeaguesResponse::class.java
                )
            }
            val leagues = data.await().leagues.filter {
                it.strSport == "Soccer" && it.idLeague != 4367
            }
            view.fillSpinner(leagues)
        }
    }
}