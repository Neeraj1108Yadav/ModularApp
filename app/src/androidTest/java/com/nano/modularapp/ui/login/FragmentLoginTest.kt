package com.nano.modularapp.ui.login

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nano.modularapp.HiltTestActivity
import com.nano.modularapp.MainActivity
import com.nano.modularapp.R
import com.nano.modularapp.launchFragmentInHiltContainer
import com.nano.modularapp.ui.signup.FragmentSignUp
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created By  on 06/08/24
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FragmentLoginTest{


    @get:Rule(order = 0)
    val hiltRule: HiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule: ActivityScenarioRule<HiltTestActivity> = ActivityScenarioRule(HiltTestActivity::class.java)

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun test_login_fragment_displayed(){

        launchFragmentInHiltContainer<FragmentLogin>(){
           /* val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            navController.setGraph(R.navigation.onboard_graph)  // Replace with your actual nav graph
            navController.setCurrentDestination(R.id.loginFragment)  // Replace with the ID of your LoginFragment
            Navigation.setViewNavController(requireView(), navController)*/
        }

        //Checking LoginFragment is attached and displayed in screen or not
        onView(withId(R.id.loginFragmentView)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_navigation_success_from_login_to_signup(){
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.setGraph(R.navigation.onboard_graph)  // Replace with your actual nav graph
        }

        launchFragmentInHiltContainer<FragmentLogin>(){
             navController.setCurrentDestination(R.id.loginFragment)
             Navigation.setViewNavController(requireView(), navController)
        }

        //Checking LoginFragment is attached and displayed in screen or not
        onView(withId(R.id.loginFragmentView)).check(ViewAssertions.matches(isDisplayed()))

        //Clicking on Sign Up text to navigate to Sign Up Fragment
        onView(withId(R.id.navigateToSignUp)).perform(ViewActions.click())

        launchFragmentInHiltContainer<FragmentSignUp>(){
            //navController.setCurrentDestination(R.id.signUpFragment)
            //Navigation.setViewNavController(requireView(), navController)
        }

        //Checking SignUpFragment is displayed or not
        onView(withId(R.id.signUpFragmentView)).check(ViewAssertions.matches(isDisplayed()))
    }
}