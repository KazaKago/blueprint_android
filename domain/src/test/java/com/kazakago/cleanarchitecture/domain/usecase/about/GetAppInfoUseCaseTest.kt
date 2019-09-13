package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.di.dataStubModule
import com.kazakago.cleanarchitecture.domain.di.domainModule
import com.kazakago.cleanarchitecture.domain.di.webStubModule
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
                    webStubModule,
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
    fun appInfo() {
        val appInfo = getAppInfoUseCase.invoke()
        Assert.assertEquals(10, appInfo.versionCode.value)
        Assert.assertEquals("1.1.0", appInfo.versionName.value)
    }
}