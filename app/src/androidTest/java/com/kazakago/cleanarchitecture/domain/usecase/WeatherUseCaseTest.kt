package com.kazakago.cleanarchitecture.domain.usecase

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.kazakago.cleanarchitecture.di.component.DaggerTestApplicationComponent
import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * WeatherUseCase Test
 *
 * Created by tamura_k on 2016/06/15.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class WeatherUseCaseTest {

    @Inject
    lateinit var weatherUseCase: WeatherUseCase

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
    fun testFetch() {
        assertThat<WeatherModel>(weatherUseCase.fetch("400040").blockingGet(), notNullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testFind() {
        assertThat<WeatherModel>(weatherUseCase.find("400040"), notNullValue())
    }

}