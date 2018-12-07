package com.task.ui.component.news;


import com.task.App;
import com.task.R;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    private final String testSearchString = "the";

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup () {
        mIdlingResource = mActivityTestRule.getActivity().getCountingIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void testSearch () {
        ViewInteraction searchEditText = onView(withId(R.id.et_search));
        searchEditText.perform(click());
        searchEditText.perform(ViewActions.typeText(testSearchString), pressImeActionButton());
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).perform(scrollTo());
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
        pressBack();
        searchEditText.check(matches(withText(testSearchString)));
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()));
    }

    @Test
    public void testScroll () {
        onView(ViewMatchers.withId(R.id.rv_news_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void testRefresh () {
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
    public void displayNewsData () {
        onView(withId(R.id.rv_news_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
    }

    @Test
    public void searchIsActive () {
        onView(withId(R.id.rl_search)).check(matches(isDisplayed()));
        onView(withId(R.id.et_search)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_search)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource () {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

    public void testEmptySearch () {
        String testSearchString = "";
        ViewInteraction searchEditText = onView(withId(R.id.et_search));
        searchEditText.perform(click());
        searchEditText.perform(ViewActions.typeText(testSearchString), pressImeActionButton());
        onView(withId(R.id.btn_search)).perform(click());
        onView(Matchers.allOf(withId(R.id.snackbar_text), withText(App.context.getString(R.string.search_error)))).check(matches(isDisplayed()));
    }

}
