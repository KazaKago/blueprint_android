package com.kazakago.cleanarchitecture.presentation.view.activity

import android.support.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

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

}