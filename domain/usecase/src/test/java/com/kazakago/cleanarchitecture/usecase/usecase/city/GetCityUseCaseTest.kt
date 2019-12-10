package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.usecase.di.dataStubModule
import com.kazakago.cleanarchitecture.usecase.di.domainModule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class GetCityUseCaseTest : KoinTest {

    private val getCityUseCase: GetCityUseCase by inject()

    @Before
    fun before() {
        startKoin {
            modules(
                listOf(
                    domainModule,
                    dataStubModule
                )
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun valid() = runBlocking {
        val cityList = getCityUseCase()
//        Assert.assertEquals(10, appInfo.versionCode.value)
//        Assert.assertEquals("1.1.0", appInfo.versionName.value)
    }

}