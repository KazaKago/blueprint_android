package com.kazakago.cleanarchitecture.presentation.common.activity

import android.support.test.rule.ActivityTestRule
import com.kazakago.cleanarchitecture.presentation.hierarchy.city.CityListActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule

class CityListActivityTest {

    @Rule
    var activityRule = ActivityTestRule(CityListActivity::class.java)

    private lateinit var activity: CityListActivity

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
