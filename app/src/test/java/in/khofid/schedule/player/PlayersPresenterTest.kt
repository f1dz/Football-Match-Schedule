package `in`.khofid.schedule.player

import `in`.khofid.schedule.TestContextProvider
import `in`.khofid.schedule.api.ApiRepository
import `in`.khofid.schedule.api.TheSportDBApi
import `in`.khofid.schedule.model.Player
import `in`.khofid.schedule.model.PlayersResponse
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayersPresenterTest {

    @Mock private lateinit var view: PlayersView
    @Mock private lateinit var gson: Gson
    @Mock private lateinit var apiRepository: ApiRepository
    @Mock private lateinit var players: List<Player>

    private lateinit var presenter: PlayersPresenter
    private val teamId = "133616"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayersPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPlayersList() {
        val response = PlayersResponse(players)

        `when` (
            gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPlayers(teamId)),
                PlayersResponse::class.java
            )
        ).thenReturn(response)

        presenter.getPlayersList(teamId)

        verify(view).showLoading()
        verify(view).showPlayersList(players)
        verify(view).hideLoading()
    }
}