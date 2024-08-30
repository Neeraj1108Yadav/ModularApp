package com.nano.modularapp.ui.login

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nano.modularapp.MainActivity
import com.nano.modularapp.R
import com.nano.modularapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created By  on 06/08/24
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FragmentLoginTest{


    @get:Rule
    val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    //@get:Rule(order = 2)
    //val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun test_blankField_expectedEmptyField(){
        println("launching hilt container")
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<FragmentLogin>()
        // Log to ensure the fragment is launched
        println("Fragment launched")

        //Checking LoginFragment is attached and displayed in screen or not
        onView(withId(R.id.loginFragment)).check(ViewAssertions.matches(isDisplayed()))

        // Log to ensure the check is done
        println("Fragment is displayed")
    }

    /*@Test
    fun test_empty_field_expectedTrue(){
        //Checking LoginFragment is attached and displayed in screen or not
        //onView(withId(R.id.loginFragment)).check(ViewAssertions.matches(isDisplayed()))

        //Clicking on Sign Up text to navigate to Sign Up Fragment
        //onView(withId(R.id.navigateToSignUp)).perform(ViewActions.click())

        //Checking SignUpFragment is displayed or not
        //onView(withId(R.id.fragmentSignUp)).check(ViewAssertions.matches(isDisplayed()))

        //Clicking Sign Up Button
        //onView(withId(R.id.btnSignUp)).perform(ViewActions.click())

        //Checking empty email error message
        //onView(withId(R.id.tvError)).check(ViewAssertions.matches(withText("Email field is empty")))
    }*/
}