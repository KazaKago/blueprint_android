package com.kazakago.cleanarchitecture.presentation.view.activity

import android.support.test.rule.ActivityTestRule

import com.squareup.spoon.Spoon

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * AboutActivityTest
 * Created by tamura_k on 2016/06/16.
 */
class AboutActivityTest {

    @Rule
    var activityRule = ActivityTestRule(AboutActivity::class.java)

    private lateinit var activity: AboutActivity

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