package `in`.khofid.schedule.team

import `in`.khofid.schedule.TestContextProvider
import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.*
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {

    @Mock private lateinit var view: TeamsView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var teams: List<Team>
    @Mock private lateinit var leagues: List<League>

    private lateinit var presenter: TeamsPresenter
    private val leagueId = 4328

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamsList() {
        val response = TeamResponse(teams)

        `when` (
            gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(leagueId.toString())),
                TeamResponse::class.java)
        ).thenReturn(response)

        presenter.getTeamsList(leagueId.toString())

        verify(view).showLoading()
        verify(view).showTeamsList(teams)
        verify(view).hideLoading()
    }

    @Test
    fun fillLeagueSpinner() {
        val response = LeaguesResponse(leagues)

        `when` (
            gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi.getLeagues()),
                LeaguesResponse::class.java
            )
        ).thenReturn(response)

        presenter.fillLeagueSpinner()
        view.fillSpinner(leagues)
    }
}