package com.kazakago.cleanarchitecture.presentation.view.activity

import android.support.test.rule.ActivityTestRule

import com.squareup.spoon.Spoon

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * MainActivity Test
 * Created by tamura_k on 2016/06/16.
 */
class MainActivityTest {

    @Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var activity: MainActivity

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = activityRule.activity
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    @Throws(Exception::class)
    fun testInitialState() {
        Spoon.screenshot(activity, "initial_state")
    }

}
