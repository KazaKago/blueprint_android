package com.kazakago.cleanarchitecture.domain.usecase;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.kazakago.cleanarchitecture.di.component.DaggerTestApplicationComponent;
import com.kazakago.cleanarchitecture.di.module.ApplicationModule;
import com.kazakago.cleanarchitecture.di.module.DataModule;
import com.kazakago.cleanarchitecture.di.module.DomainModule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

/**
 * CityUseCase Test
 * <p>
 * Created by tamura_k on 2016/06/14.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class CityUseCaseTest {

    @Inject
    public CityUseCase cityUseCase;

    @Before
    public void setUp() throws Exception {
        DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(InstrumentationRegistry.getTargetContext()))
                .domainModule(new DomainModule())
                .dataModule(new DataModule())
                .build()
                .inject(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindAll() throws Exception {
        assertThat(cityUseCase.findAll().toList().blockingGet(), hasSize(greaterThan(0)));
    }

}