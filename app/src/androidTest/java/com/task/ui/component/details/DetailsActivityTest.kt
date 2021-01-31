package com.task.ui.component.details

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.task.R
import com.task.RECIPE_ITEM_KEY
import com.task.TestUtil.initData
import com.task.TestUtil.recipes
import com.task.utils.EspressoIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by AhmedEltaher
 */

@HiltAndroidTest
class DetailsActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityTestRule = ActivityTestRule(DetailsActivity::class.java, true, false)
    private var mIdlingResource: IdlingResource? = null


    @Before
    fun setup() {
        initData()
        val intent: Intent = Intent().apply {
            putExtra(RECIPE_ITEM_KEY, recipes.recipesList[0])
        }
        mActivityTestRule.launchActivity(intent)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun testRecipeDetails() {
        //Assert Title
        onView(withId(R.id.tv_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_name)).check(matches(withText(recipes.recipesList[0].name)))
        //Assert Recipe Description
        onView(withId(R.id.tv_description)).perform(scrollTo())
        onView(withId(R.id.tv_description)).check(matches(withText(recipes.recipesList[0].description)))
        //Assert Add to Favorite
        onView(withId(R.id.add_to_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.add_to_favorite)).check(matches(isClickable()))
    }

    @After
    fun unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister()
        }
    }
}
