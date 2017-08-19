package com.kazakago.cleanarchitecture.domain.usecase

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.lazy
import com.kazakago.cleanarchitecture.di.applicationModule
import com.kazakago.cleanarchitecture.di.dataModule
import com.kazakago.cleanarchitecture.di.domainModule
import com.kazakago.cleanarchitecture.di.webModule
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

/**
 * AboutUseCase Test
 *
 * Created by tamura_k on 2016/06/14.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class AppInfoUseCaseTest : KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(applicationModule(InstrumentationRegistry.getTargetContext()))
        import(domainModule())
        import(dataModule())
        import(webModule())
    }

    private val getAppVersionUseCase: GetAppVersionUseCase by lazy.instance()
    private val getMailAddressUrlUseCase: GetMailAddressUrlUseCase by lazy.instance()
    private val getOfficialSiteUrlUseCase: GetOfficialSiteUrlUseCase by lazy.instance()
    private val getPlayStoreUrlUseCase: GetPlayStoreUrlUseCase by lazy.instance()

    @Before
    @Throws(Exception::class)
    fun setUp() {
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