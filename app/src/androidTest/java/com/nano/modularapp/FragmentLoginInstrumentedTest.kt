package com.nano.modularapp

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created By Neeraj Yadav on 26/07/24
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FragmentLoginInstrumentedTest {

    @get:Rule
    val hiltRule:HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun test_blankField_expectedEmptyField(){
        //Checking LoginFragment is attached and displayed in screen or not
        onView(withId(R.id.fragmentLogin)).check(ViewAssertions.matches(isDisplayed()))

        //Clicking on Sign Up text to navigate to Sign Up Fragment
        //onView(withId(R.id.navigateToSignUp)).perform(ViewActions.click())

        //Checking SignUpFragment is displayed or not
        //onView(withId(R.id.fragmentSignUp)).check(ViewAssertions.matches(isDisplayed()))

        //Clicking Sign Up Button
        //onView(withId(R.id.btnSignUp)).perform(ViewActions.click())

        //Checking empty email error message
        //onView(withId(R.id.tvError)).check(ViewAssertions.matches(withText("Email field is empty")))
    }

}