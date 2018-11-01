package `in`.khofid.schedule.search

import `in`.khofid.schedule.R
import `in`.khofid.schedule.match.MatchViewHolder
import android.os.SystemClock
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
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
class SearchMatchActivityTest {

    @Rule
    @JvmField var activityRule  = ActivityTestRule(SearchMatchActivity::class.java)

    @Test
    fun testBehavior(){
        onView(withId(R.id.search)).check(matches(isDisplayed()))
        onView(withId(R.id.search)).perform(ViewActions.click())
        onView(isAssignableFrom(EditText::class.java)).perform(typeText("Chelsea"))
        onView(withId(R.id.match_rv)).check(matches(isDisplayed()))
        onView(allOf(isDisplayed(),withId(R.id.match_rv))).perform(RecyclerViewActions.actionOnItemAtPosition<MatchViewHolder>(0, click()))
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        SystemClock.sleep(1000)
        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(withId(android.support.design.R.id.snackbar_text))).check(matches(isDisplayed()))

        SystemClock.sleep(1000)
        Espresso.pressBack()
    }
}