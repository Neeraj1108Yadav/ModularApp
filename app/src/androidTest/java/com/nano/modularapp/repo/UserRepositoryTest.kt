package com.nano.modularapp.repo

import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.nano.modularapp.InputStreamFile
import com.nano.modularapp.MockServer
import com.nano.modularapp.api.UserService
import com.nano.modularapp.di.NetworkModule
import com.nano.modularapp.model.UserRequest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

/**
 * Created By  on 07/08/24
 */
@RunWith(JUnit4::class)
@HiltAndroidTest
@UninstallModules(NetworkModule::class) // Uninstall the production level module
class UserRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userService: UserService

    private lateinit var testDispatcher: TestDispatcher

    private val gson = Gson()

    @Before
    fun setUp() {
        hiltRule.inject()
        testDispatcher = UnconfinedTestDispatcher()
        //Dispatchers.setMain(testDispatcher)
        MockServer.server.start(8080)
    }

    @Test
    fun test_login_response() = runTest{
        val userRequest = UserRequest(
            email = "neerajhome40@yopmail.com",
            os = "Android",
            password = "123456"
        )

        val inputStreamFile = InstrumentationRegistry.getInstrumentation().context.resources.assets.open("login.json")
        val file = InputStreamFile.getFile(inputStreamFile)
        MockServer.server.enqueue(MockResponse().setBody(file))

        val result = userService.login(userRequest)

        val json = gson.toJson(result.body())
        val resultResponse = JsonParser.parseString(json)
        val expectedResponse = JsonParser.parseString(file)

        assertEquals(expectedResponse.asJsonObject.get("_id"),resultResponse.asJsonObject.get("_id"))
    }

    @After
    fun tearDown() {
        MockServer.server.shutdown()
        //Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }
}