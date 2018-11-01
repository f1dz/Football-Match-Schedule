package `in`.khofid.schedule.home

import `in`.khofid.schedule.R
import `in`.khofid.schedule.match.MatchViewHolder
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Home01MatchesTest {
    @Rule
    @JvmField var activityRule  = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testMatchesBehaviour() {

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
    }
}