package com.kazakago.cleanarchitecture.domain.usecase

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.kazakago.cleanarchitecture.di.component.DaggerTestApplicationComponent
import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.di.module.WebModule
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetAppVersionUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetMailAddressUrlUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetOfficialSiteUrlUseCase
import com.kazakago.cleanarchitecture.domain.usecase.appInfo.GetPlayStoreUrlUseCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.After
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
class AppInfoUseCaseTest {

    @Inject
    lateinit var getAppVersionUseCase: GetAppVersionUseCase
    @Inject
    lateinit var getMailAddressUrlUseCase: GetMailAddressUrlUseCase
    @Inject
    lateinit var getOfficialSiteUrlUseCase: GetOfficialSiteUrlUseCase
    @Inject
    lateinit var getPlayStoreUrlUseCase: GetPlayStoreUrlUseCase

    @Before
    @Throws(Exception::class)
    fun setUp() {
        DaggerTestApplicationComponent.builder()
                .applicationModule(ApplicationModule(InstrumentationRegistry.getTargetContext()))
                .domainModule(DomainModule())
                .dataModule(DataModule())
                .webModule(WebModule())
                .build()
                .inject(this)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    @Test
    @Throws(Exception::class)
    fun testGetAppVersion() {
        assertThat(getAppVersionUseCase.execute(Unit), notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetMailAddressUrl() {
        assertThat(getMailAddressUrlUseCase.execute(Unit), notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetOfficialSiteUrl() {
        assertThat(getOfficialSiteUrlUseCase.execute(Unit), notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testGetPlayStoreUrl() {
        assertThat(getPlayStoreUrlUseCase.execute(Unit), notNullValue())
    }

}