package `in`.khofid.schedule.home

import `in`.khofid.schedule.R
import `in`.khofid.schedule.match.MatchViewHolder
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.EditText
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule  = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testMatchesBehaviour() {

        // Testing Matches

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.spinner))).check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).perform(RecyclerViewActions.scrollToPosition<MatchViewHolder>(14))
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(14, click()))

        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(allOf(withText("LAST MATCH"))).perform(click())
        SystemClock.sleep(1000)
        onView(allOf(isDisplayed(), withId(R.id.spinner))).check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).perform(RecyclerViewActions.scrollToPosition<MatchViewHolder>(14))
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).perform(RecyclerViewActions.scrollToPosition<MatchViewHolder>(0))
        onView(allOf(isDisplayed(), withId(R.id.match_rv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))

        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))

        Espresso.pressBack()

        // Testing Teams

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.teams)).perform(click())
        onView(withId(R.id.league_spinner)).check(matches(isDisplayed()))
        onView(withId(R.id.teams_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.league_spinner)).perform(click())
        onView(withText("Italian Serie A")).perform(click())

        SystemClock.sleep(1000)

        onView(withId(R.id.search)).check(matches(isDisplayed()))
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(ViewActions.typeText("Juventus"))
        onView(allOf(isDisplayed(), withId(R.id.teams_rv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))

        SystemClock.sleep(1000)

        onView(allOf(withId(R.id.overview))).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))
        onView(allOf(withText("PLAYERS"))).perform(click())
        onView(withId(R.id.players_rv)).check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.players_rv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))

        SystemClock.sleep(1000)

        Espresso.pressBack()
        Espresso.pressBack()

        // Testing Favorites

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())
        onView(allOf(isDisplayed(), withId(R.id.favoritesRv))).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(allOf(isDisplayed(), withId(R.id.favoritesRv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))
        onView(withId(R.id.match_date)).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))
        Espresso.pressBack()
        onView(allOf(withText("TEAMS"))).perform(click())
        onView(allOf(isDisplayed(), withId(R.id.teams_rv))).check(matches(isDisplayed()))
        onView(allOf(isDisplayed(), withId(R.id.teams_rv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))
        onView(allOf(withId(R.id.overview))).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))
        Espresso.pressBack()
    }
}