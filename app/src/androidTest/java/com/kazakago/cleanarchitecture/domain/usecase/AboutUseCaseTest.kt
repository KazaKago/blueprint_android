package com.kazakago.cleanarchitecture.domain.usecase

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.kazakago.cleanarchitecture.di.component.DaggerTestApplicationComponent
import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * AboutUseCase Test
 *
 * Created by tamura_k on 2016/06/14.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class AboutUseCaseTest {

    @Inject
    lateinit var aboutUseCase: AboutUseCase

    @Before
    @Throws(Exception::class)
    fun setUp() {
        DaggerTestApplicationComponent.builder()
                .applicationModule(ApplicationModule(InstrumentationRegistry.getTargetContext()))
                .domainModule(DomainModule())
                .dataModule(DataModule())
                .build()
                .inject(this)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    @Throws(Exception::class)
    fun testGetPlayStoreUrl() {
        assertThat(aboutUseCase.playStoreUrl, notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetMailUrl() {
        assertThat(aboutUseCase.mailUrl, notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetWebSiteUrl() {
        assertThat(aboutUseCase.webSiteUrl, notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetCurrentVersion() {
        assertThat(aboutUseCase.currentVersion, notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetCurrentYear() {
        assertThat(aboutUseCase.currentYear, notNullValue())
    }

}