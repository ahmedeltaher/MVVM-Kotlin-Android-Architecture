package com.task.ui.component.news

import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.task.R
import com.task.ui.component.details.DetailsActivity
import com.task.utils.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsListActivityTest {
    private val testSearchString = "President"

    @get:Rule
    var mActivityTestRule = ActivityTestRule(NewsListActivity::class.java)
    @get:Rule
    var detailsActivityTestRule = ActivityTestRule(DetailsActivity::class.java)
    private var mIdlingResource: IdlingResource? = null

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    //TODO need to be checked and to be fixed
    @Test
    fun testSearch() {
        onView(withId(R.id.action_search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText(testSearchString))
                .perform(pressKey(KeyEvent.KEYCODE_ENTER))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).perform(scrollTo())
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }

    @Test
    fun testScroll() {
        onView(withId(R.id.rv_news_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
    }

    @Test
    fun testRefresh() {
        //Before refresh there is a list .
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
        // do refresh .
        onView(withId(R.id.action_favorite)).perform(click())
        //after refresh there is a list.
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
    }

    @Test
    fun displayNewsData() {
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()))
        onView(withId(R.id.pb_loading)).check(matches(not(isDisplayed())))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}