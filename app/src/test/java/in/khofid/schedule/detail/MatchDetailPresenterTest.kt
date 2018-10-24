package `in`.khofid.schedule.detail

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

class MatchDetailPresenterTest {

    @Mock private lateinit var view: MatchDetailView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var match: Match
    @Mock private lateinit var teams: List<Team>
    @Mock private lateinit var matchDetail: MatchDetail
    @Mock private lateinit var listMatchDetail: List<MatchDetail>

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetail() {
        val response = MatchDetailResponse(listMatchDetail)
        val matchId = 576550

        `when` (
            gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetail(matchId)), MatchDetailResponse::class.java)
        ).thenReturn(response)

        presenter.getMatchDetail(matchId)

        verify(view).showLoading()
        verify(view).showMatch(listMatchDetail)
        verify(view).hideLoading()
    }

    @Test
    fun getTeamDetail() {
        val response = TeamResponse(teams)

        `when` (gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(match.homeTeamId!!)), TeamResponse::class.java))
            .thenReturn(response)

        `when` (gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(match.awayTeamId!!)), TeamResponse::class.java))
            .thenReturn(response)

        presenter.getTeamDetail(matchDetail)

        val badges = arrayListOf(teams.first(), teams.last())

        verify(view).showLoading()
        verify(view).showBadge(badges)
        verify(view).hideLoading()
    }
}