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
import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.usecase.city.GetCityUseCase
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.hasSize
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * CityUseCase Test
 *
 *
 * Created by tamura_k on 2016/06/14.
 */
@RunWith(AndroidJUnit4::class)
@SmallTest
class CityUseCaseTest: KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(applicationModule(InstrumentationRegistry.getTargetContext()))
        import(domainModule())
        import(dataModule())
        import(webModule())
    }

    private val getCityUseCase: GetCityUseCase by lazy.instance()

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
    fun testFindAll() {
        val cityList = getCityUseCase.execute(Unit)
                .toList()
                .blockingGet()
        assertThat<List<CityModel>>(cityList, hasSize<Any>(greaterThan(0)))
    }

}