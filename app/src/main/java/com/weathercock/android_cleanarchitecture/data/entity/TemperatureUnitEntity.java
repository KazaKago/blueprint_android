package com.weathercock.android_cleanarchitecture.data.entity;

import io.realm.RealmObject;

/**
 * Temperature Unit Entity
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class TemperatureUnitEntity extends RealmObject {

    //摂氏
    private Float celsius;
    //華氏
    private Float fahrenheit;

    public Float getCelsius() {
        return celsius;
    }

    public void setCelsius(Float celsius) {
        this.celsius = celsius;
    }

    public Float getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(Float fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

}
