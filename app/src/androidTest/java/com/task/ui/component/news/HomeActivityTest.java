package com.task.ui.component.news;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.task.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.task.utils.ResourcesUtil.getString;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    private final String testSearchString = "the";

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup() {
        mIdlingResource = mActivityTestRule.getActivity().getCountingIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void testSearch() {
        ViewInteraction searchEditText = onView(withId(R.id.et_search));
        searchEditText.perform(click());
        searchEditText.perform(ViewActions.typeText(testSearchString), pressImeActionButton());
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
        pressBack();
        searchEditText.check(matches(withText(testSearchString)));
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testScroll() {
        onView(ViewMatchers.withId(R.id.rv_news_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void testRefresh() {
        //Before refresh there is a list .
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
        // do refresh .
        onView(withId(R.id.ic_toolbar_refresh)).perform(click());
        //after refresh there is a list.
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
    }

    @Test
    public void displayNewsData() {
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
    }

    @Test
    public void searchIsActive() {
        onView(withId(R.id.rl_search)).check(matches(isDisplayed()));
        onView(withId(R.id.et_search)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    public void testEmptySearch() {
        String testSearchString = "";
        ViewInteraction searchEditText = onView(withId(R.id.et_search));
        searchEditText.perform(click());
        searchEditText.perform(ViewActions.typeText(testSearchString), pressImeActionButton());
        onView(withId(R.id.btn_search)).perform(click());
        onView(allOf(withId(android.support.design.R.id.snackbar_text),
                withText(getString(R.string.search_error))))
                .check(matches(isDisplayed()));
    }

}
