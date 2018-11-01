package `in`.khofid.schedule.home

import `in`.khofid.schedule.R
import `in`.khofid.schedule.match.MatchViewHolder
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
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
class Home02TeamsTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testTeamsBehaviour() {

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.teams)).perform(click())
        onView(withId(R.id.league_spinner)).check(matches(isDisplayed()))
        onView(withId(R.id.teams_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.league_spinner)).perform(click())
        onView(withText("Italian Serie A")).perform(click())

        SystemClock.sleep(1000)

        onView(withId(R.id.search)).check(matches(isDisplayed()))
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Juventus"))
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
    }
}