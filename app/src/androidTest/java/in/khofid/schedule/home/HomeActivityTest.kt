package `in`.khofid.schedule.home

import `in`.khofid.schedule.R
import `in`.khofid.schedule.home.CustomAssertions.Companion.hasItemCount
import `in`.khofid.schedule.match.MatchAdapter
import `in`.khofid.schedule.match.MatchViewHolder
import `in`.khofid.schedule.model.Match
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import kotlinx.android.synthetic.main.match_layout.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.matchers.JUnitMatchers.containsString
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule  = ActivityTestRule(HomeActivity::class.java)

    /**
     * Testing Behaviour, assumption there are no favorites matches at this moment
     */

    @Test
    fun testAppBehaviour() {

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.match_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.match_rv)).check(hasItemCount(15))
        onView(withId(R.id.match_rv)).perform(RecyclerViewActions.scrollToPosition<MatchViewHolder>(14))
        onView(withId(R.id.match_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(14, click()))
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))

        SystemClock.sleep(1000)

        onView(withId(R.id.add_to_favorite)).perform(click())

        try {
            onView(withText("Added to favorite")).check(matches(isDisplayed()))
        } catch (e: Exception){
            onView(withText("Removed from favorite")).check(matches(isDisplayed()))
        }

        Espresso.pressBack()

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.next_match)).perform(click())
        onView(withId(R.id.match_rv)).check(matches(isDisplayed()))
        onView(withId(R.id.match_rv)).check(hasItemCount(15))
        onView(withId(R.id.match_rv)).perform(RecyclerViewActions.scrollToPosition<MatchViewHolder>(14))
        onView(withId(R.id.match_rv)).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(14, click()))
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(withId(R.id.add_to_favorite)).perform(click())

        try {
            onView(withText("Added to favorite")).check(matches(isDisplayed()))
        } catch (e: Exception){
            onView(withText("Removed from favorite")).check(matches(isDisplayed()))
        }

        Espresso.pressBack()

        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites)).perform(click())
        onView(withId(R.id.favoritesRv)).check(matches(isDisplayed()))
        onView(withId(R.id.favoritesRv)).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))
        SystemClock.sleep(1000)
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(withText("Removed from favorite")).check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withId(R.id.swipe_refresh)).perform(swipeDown())
    }
}