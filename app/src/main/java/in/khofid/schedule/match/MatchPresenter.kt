package `in`.khofid.schedule.match

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.db.database
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.MatchResponse
import `in`.khofid.schedule.model.Team
import `in`.khofid.schedule.model.TeamResponse
import `in`.khofid.schedule.utils.CoroutineContextProvider
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.selects.select
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.uiThread

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
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

    fun processBadge(ctx: Context, match: List<Match>) {
        async(context.main) {
            match.forEach {
                bg {
                    var homeTeams: List<Team> = listOf()
                    var awayTeams: List<Team> = listOf()
                    try {
                        ctx.database.use {
                            val result = select(Team.TABLE_TEAM)
                                .whereArgs("(${Team.TEAM_ID} = {id})", "id" to it.homeTeamId!!)
                            homeTeams = result.parseList(classParser())
                        }

                        ctx.database.use {
                            val result = select(Team.TABLE_TEAM)
                                .whereArgs("(${Team.TEAM_ID} = {id})", "id" to it.awayTeamId!!)
                            awayTeams = result.parseList(classParser())
                        }

                    } catch (e: SQLiteConstraintException) {
                    }

                    if (homeTeams.isEmpty()) {
                        // Get data
                        val response = Gson().fromJson(
                            ApiRepository().doRequest(
                                TheSportDBApi.getTeamDetail(it.homeTeamId!!)
                            ),
                            TeamResponse::class.java
                        )

                        try {
                            ctx.database.use {
                                with(response.teams.first()){
                                    insert(
                                        Team.TABLE_TEAM,
                                        Team.TEAM_ID to idTeam,
                                        Team.TEAM_NAME to strTeam,
                                        Team.TEAM_ALTERNATE to strAlternate,
                                        Team.TEAM_BADGE to strTeamBadge,
                                        Team.TEAM_FORMED_YEAR to intFormedYear,
                                        Team.TEAM_STADIUM to strStadium,
                                        Team.TEAM_STADIUM_THUMB to strStadiumThumb,
                                        Team.TEAM_STADIUM_LOCATION to strStadiumLocation,
                                        Team.TEAM_STADIUM_CAPACITY to intStadiumCapacity,
                                        Team.TEAM_DESCRIPTION to strDescriptionEN,
                                        Team.TEAM_MANAGER to strManager,
                                        Team.TEAM_WEBSITE to strWebsite,
                                        Team.TEAM_FACEBOOK to strFacebook,
                                        Team.TEAM_TWITTER to strTwitter,
                                        Team.TEAM_INSTAGRAM to strInstagram,
                                        Team.TEAM_COUNTRY to strCountry,
                                        Team.TEAM_BANNER to strTeamBanner,
                                        Team.TEAM_YOUTUBE to strYoutube
                                    )
                                }

                            }
                        } catch (e: SQLiteConstraintException) {
                            Log.e("ERROR", e.localizedMessage)
                        }
                    }

                    if (awayTeams.isEmpty()) {
                        // Get data
                        val response = Gson().fromJson(
                            ApiRepository().doRequest(
                                TheSportDBApi.getTeamDetail(it.homeTeamId!!)
                            ),
                            TeamResponse::class.java
                        )

                        try {
                            ctx.database.use {
                                with(response.teams.first()){
                                    insert(
                                        Team.TABLE_TEAM,
                                        Team.TEAM_ID to idTeam,
                                        Team.TEAM_NAME to strTeam,
                                        Team.TEAM_ALTERNATE to strAlternate,
                                        Team.TEAM_BADGE to strTeamBadge,
                                        Team.TEAM_FORMED_YEAR to intFormedYear,
                                        Team.TEAM_STADIUM to strStadium,
                                        Team.TEAM_STADIUM_THUMB to strStadiumThumb,
                                        Team.TEAM_STADIUM_LOCATION to strStadiumLocation,
                                        Team.TEAM_STADIUM_CAPACITY to intStadiumCapacity,
                                        Team.TEAM_DESCRIPTION to strDescriptionEN,
                                        Team.TEAM_MANAGER to strManager,
                                        Team.TEAM_WEBSITE to strWebsite,
                                        Team.TEAM_FACEBOOK to strFacebook,
                                        Team.TEAM_TWITTER to strTwitter,
                                        Team.TEAM_INSTAGRAM to strInstagram,
                                        Team.TEAM_COUNTRY to strCountry,
                                        Team.TEAM_BANNER to strTeamBanner,
                                        Team.TEAM_YOUTUBE to strYoutube
                                    )
                                }
                            }
                        } catch (e: SQLiteConstraintException) {
                            Log.d("LOG", e.localizedMessage)
                        }
                    }
                }
            }
                view.processBadge()
        }
    }
}