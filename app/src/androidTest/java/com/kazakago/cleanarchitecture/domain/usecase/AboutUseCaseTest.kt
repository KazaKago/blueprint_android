package com.kazakago.cleanarchitecture.domain.usecase

///**
// * AboutUseCase Test
// *
// * Created by tamura_k on 2016/06/14.
// */
//@RunWith(AndroidJUnit4::class)
//@SmallTest
//class AboutUseCaseTest {
//
//    @Inject
//    lateinit var aboutUseCase: AboutUseCase
//
//    @Before
//    @Throws(Exception::class)
//    fun setUp() {
//        DaggerTestApplicationComponent.builder()
//                .applicationModule(ApplicationModule(InstrumentationRegistry.getTargetContext()))
//                .domainModule(DomainModule())
//                .dataModule(DataModule())
//                .build()
//                .inject(this)
//    }
//
//    @After
//    @Throws(Exception::class)
//    fun tearDown() {
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testGetPlayStoreUrl() {
//        assertThat(aboutUseCase.playStoreUrl, notNullValue())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testGetMailUrl() {
//        assertThat(aboutUseCase.mailUrl, notNullValue())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testGetWebSiteUrl() {
//        assertThat(aboutUseCase.webSiteUrl, notNullValue())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testGetCurrentVersion() {
//        assertThat(aboutUseCase.currentVersion, notNullValue())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testGetCurrentYear() {
//        assertThat(aboutUseCase.currentYear, notNullValue())
//    }
//
//}