package `in`.khofid.schedule.search

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.TeamResponse
import `in`.khofid.schedule.utils.CoroutineContextProvider
import `in`.khofid.schedule.utils.dbGetTeam
import `in`.khofid.schedule.utils.insertToDb
import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(
    private val view: SearchMatchView,
    private val apiRepository: ApiRepository = ApiRepository(),
    private val gson: Gson = Gson(),
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun searchMatches(query: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.searchEvents(query)),
                    SearchMatchResponse::class.java
                )
            }
            view.showMatchesList(data.await().event)
            view.hideLoading()
        }
    }

    fun processBadge(ctx: Context, matches: List<Match>) {
        async (context.main) {
            matches.forEach {
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
}