package `in`.khofid.schedule

import `in`.khofid.schedule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}