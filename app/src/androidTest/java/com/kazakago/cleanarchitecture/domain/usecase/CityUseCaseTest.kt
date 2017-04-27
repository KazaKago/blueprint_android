package com.kazakago.cleanarchitecture.domain.usecase

///**
// * CityUseCase Test
// *
// *
// * Created by tamura_k on 2016/06/14.
// */
//@RunWith(AndroidJUnit4::class)
//@SmallTest
//class CityUseCaseTest {
//
//    @Inject
//    lateinit var cityUseCase: CityUseCase
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
//    fun testFindAll() {
//        assertThat<List<CityModel>>(cityUseCase.findAll().toList().blockingGet(), hasSize<Any>(greaterThan(0)))
//    }
//
//}