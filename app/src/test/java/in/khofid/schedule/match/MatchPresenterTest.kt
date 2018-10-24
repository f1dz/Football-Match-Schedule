package `in`.khofid.schedule.match

import `in`.khofid.schedule.TestContextProvider
import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.Match
import `in`.khofid.schedule.model.MatchResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock private lateinit var view: MatchView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var matches: List<Match>

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getLastMatchList() {
        val response = MatchResponse(matches)

        `when` (
            gson.fromJson(apiRepository.doRequest(TheSportDBApi.getLastMatch()), MatchResponse::class.java)
        ).thenReturn(response)

        presenter.getLastMatchList()

        verify(view).showLoading()
        verify(view).showMatchList(matches)
        verify(view).hideLoading()
    }

    @Test
    fun getNextMatchList() {
        val response = MatchResponse(matches)

        `when` (
            gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch()), MatchResponse::class.java)
        ).thenReturn(response)

        presenter.getNextMatchList()

        verify(view).showLoading()
        verify(view).showMatchList(matches)
        verify(view).hideLoading()
    }
}