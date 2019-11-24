package com.task.ui.component.news

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.task.App
import com.task.R
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class NewsListActivityTest {
    private val testSearchString = "the"
    @get:Rule
    var mActivityTestRule = ActivityTestRule(NewsListActivity::class.java)
    private var mIdlingResource: IdlingResource? = null
    @Before
    fun setup() {
        mIdlingResource = mActivityTestRule.activity.countingIdlingResource
        IdlingRegistry.getInstance().register(mIdlingResource)
    }

    @Test
    fun testSearch() {
        val searchEditText = Espresso.onView(ViewMatchers.withId(R.id.et_search))
        searchEditText.perform(ViewActions.click())
        searchEditText.perform(ViewActions.typeText(testSearchString), ViewActions.pressImeActionButton())
        Espresso.onView(ViewMatchers.withId(R.id.btn_search)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_description)).perform(ViewActions.scrollTo())
        Espresso.onView(ViewMatchers.withId(R.id.tv_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBack()
        searchEditText.check(ViewAssertions.matches(ViewMatchers.withText(testSearchString)))
        Espresso.onView(ViewMatchers.withId(R.id.rv_news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testScroll() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_news_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_description)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRefresh() { //Before refresh there is a list .
        Espresso.onView(ViewMatchers.withId(R.id.rv_news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        // do refresh .
        Espresso.onView(ViewMatchers.withId(R.id.ic_toolbar_refresh)).perform(ViewActions.click())
        //after refresh there is a list.
        Espresso.onView(ViewMatchers.withId(R.id.rv_news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun displayNewsData() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_news_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pb_loading)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun searchIsActive() {
        Espresso.onView(ViewMatchers.withId(R.id.rl_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.et_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.btn_search)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource)
        }
    }

    fun testEmptySearch() {
        val testSearchString = ""
        val searchEditText = Espresso.onView(ViewMatchers.withId(R.id.et_search))
        searchEditText.perform(ViewActions.click())
        searchEditText.perform(ViewActions.typeText(testSearchString), ViewActions.pressImeActionButton())
        Espresso.onView(ViewMatchers.withId(R.id.btn_search)).perform(ViewActions.click())
        Espresso.onView(Matchers.allOf(ViewMatchers.withId(R.id.snackbar_text), ViewMatchers.withText(App.context.getString(R.string.search_error)))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}