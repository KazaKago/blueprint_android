package com.kazakago.cleanarchitecture.presentation.view.activity

import android.support.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

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

}
