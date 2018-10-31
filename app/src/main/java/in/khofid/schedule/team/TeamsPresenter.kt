package `in`.khofid.schedule.team

import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.LeaguesResponse
import `in`.khofid.schedule.model.TeamResponse
import `in`.khofid.schedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository = ApiRepository(),
                     private val gson: Gson = Gson(),
                     private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamsList(league: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
                )
            }
            val teams = data.await().teams
            if(teams == null)
                view.teamsNotFound()
            else {
                view.showTeamsList(teams)
                view.hideLoading()
            }
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