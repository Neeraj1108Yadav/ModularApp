package com.nano.modularapp.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Created By  on 01/08/24
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NetworkUtilTest{

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var networkUtil: NetworkUtil

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun test_internet_connection_expectedTrue(){

        val isNetworkAvailable = networkUtil.isInternetConnectionAvailable()
        assertEquals(true,isNetworkAvailable)
    }
}