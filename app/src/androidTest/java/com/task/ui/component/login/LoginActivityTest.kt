package com.task.ui.component.login

import android.widget.Toast
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.task.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep


/**
 * Created by AhmedEltaher
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    var mActivityTestRule = IntentsTestRule(LoginActivity::class.java, true, false)

    @Before
    fun setup() {
    }

    @Test
    fun testLoginSuccess() {
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        onView(withId(R.id.password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        onView(withId(R.id.username)).perform(clearText())
        onView(withId(R.id.password)).perform(clearText())
        onView(withId(R.id.username)).perform(typeText("ahmed@ahmed.ahmed"))
        onView(withId(R.id.password)).perform(typeText("ahmed"))
        onView(withId(R.id.login)).perform(click())
        Intents.intended(hasComponent(hasClassName("com.task.ui.component.recipes.RecipesListActivity")))
    }

    @Test
    fun testWrongUserName() {
        sleep(Toast.LENGTH_LONG * 2L)
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        onView(withId(R.id.password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        onView(withId(R.id.username)).perform(clearText())
        onView(withId(R.id.password)).perform(clearText())
        onView(withId(R.id.username)).perform(typeText(""))
        onView(withId(R.id.password)).perform(typeText("ahmed"))
        sleep(Toast.LENGTH_LONG.toLong())
        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.invalid_username)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    @Test
    fun testWrongPassword() {
        sleep(Toast.LENGTH_LONG * 2L)
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        onView(withId(R.id.password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        onView(withId(R.id.username)).perform(clearText())
        onView(withId(R.id.password)).perform(clearText())
        onView(withId(R.id.username)).perform(typeText("ahmed@ahmed.ahmed"))
        onView(withId(R.id.password)).perform(typeText(""))
        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.invalid_password)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    @Test
    fun testWrongUserNameAndPassword() {
        sleep(Toast.LENGTH_LONG * 2L)
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        onView(withId(R.id.password)).check(matches(isDisplayed()))
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        onView(withId(R.id.username)).perform(clearText())
        onView(withId(R.id.password)).perform(clearText())
        onView(withId(R.id.username)).perform(typeText(" "))
        onView(withId(R.id.password)).perform(typeText(" "))
        onView(withId(R.id.login)).perform(click())

        onView(withText(R.string.invalid_username_and_password)).inRoot(withDecorView(not(`is`(mActivityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    @After
    fun shutDown() {
    }
}
