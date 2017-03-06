package com.kazakago.cleanarchitecture.data.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WeatherRetrofit from livedoor
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class WeatherRetrofit {

    public static Retrofit getInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://weather.livedoor.com/forecast/webservice/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
