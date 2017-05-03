package com.kazakago.cleanarchitecture.domain.usecase

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.kazakago.cleanarchitecture.di.component.DaggerTestApplicationComponent
import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import com.kazakago.cleanarchitecture.di.module.WebModule
import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * CityUseCase Test
 *
 *
 * Created by tamura_k on 2016/06/14.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class CityUseCaseTest {

    @Inject
    lateinit var getCityUseCase: GetCityUseCase

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
    fun testFindAll() {
        val cityList = getCityUseCase.execute(Unit)
                .toList()
                .blockingGet()
        assertThat<List<CityModel>>(cityList, hasSize<Any>(greaterThan(0)))
    }

}