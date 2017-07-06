package com.kazakago.cleanarchitecture.domain.usecase

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.kazakago.cleanarchitecture.di.component.DaggerTestApplicationComponent
import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.di.module.WebModule
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.usecase.weather.GetWeatherUseCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.After
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
    lateinit var getWeatherUseCase: GetWeatherUseCase

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
    fun testGetWeather() {
        assertThat<WeatherModel>(getWeatherUseCase.execute("400040").blockingGet(), notNullValue())
    }

}