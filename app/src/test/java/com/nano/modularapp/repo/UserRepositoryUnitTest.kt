package com.nano.modularapp.repo

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.nano.modularapp.Helper
import com.nano.modularapp.api.UserService
import com.nano.modularapp.model.UserRequest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
class UserRepositoryUnitTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var mockWebServer: MockWebServer

    private val gson = Gson()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `test user login mock web server`() = runTest{
        val userRequest = UserRequest(
            email = "neerajhome40@yopmail.com",
            os = "Android",
            password = "123456"
        )
        //Arrange
        val mockData = Helper.getJsonFile("login.json")
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockData)
        mockWebServer.enqueue(mockResponse)

        //Act
        val response = userService.login(userRequest)

        val json = gson.toJson(response.body())
        val resultResponse = JsonParser.parseString(json)
        val expectedResponse = JsonParser.parseString(mockData)

        //Assert
        assertEquals(expectedResponse.asJsonObject.get("_id"),resultResponse.asJsonObject.get("_id"))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}