package `in`.khofid.schedule.search

import `in`.khofid.schedule.TestContextProvider
import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.Match
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class SearchMatchPresenterTest {

    @Mock private lateinit var view: SearchMatchView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var matches: List<Match>

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun searchMatches() {
        val query = "Juventus"
        val response = SearchMatchResponse(matches)

        `when`(
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.searchEvents(query)),
                SearchMatchResponse::class.java
            )
        ).thenReturn(response)

        presenter.searchMatches(query)

        verify(view).showLoading()
        verify(view).showMatchesList(matches)
        verify(view).hideLoading()

    }
}