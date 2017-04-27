package com.kazakago.cleanarchitecture.domain.usecase

///**
// * WeatherUseCase Test
// *
// * Created by tamura_k on 2016/06/15.
// */
//@RunWith(AndroidJUnit4::class)
//@SmallTest
//class WeatherUseCaseTest {
//
//    @Inject
//    lateinit var weatherUseCase: WeatherUseCase
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
//    fun testFetch() {
//        assertThat<WeatherModel>(weatherUseCase.fetch("400040").blockingGet(), notNullValue())
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun testFind() {
//        assertThat<WeatherModel>(weatherUseCase.find("400040"), notNullValue())
//    }
//
//}