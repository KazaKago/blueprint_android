package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.usecase.di.dataStubModule
import com.kazakago.cleanarchitecture.usecase.di.domainModule
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class GetAppInfoUseCaseTest : KoinTest {

    private val getAppInfoUseCase: GetAppInfoUseCase by inject()

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
    fun valid() {
        val appInfo = getAppInfoUseCase()
        Assert.assertEquals(10, appInfo.versionCode.value)
        Assert.assertEquals("1.1.0", appInfo.versionName.value)
    }

}