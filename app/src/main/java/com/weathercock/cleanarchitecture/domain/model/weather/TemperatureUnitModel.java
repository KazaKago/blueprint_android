package com.weathercock.cleanarchitecture.domain.model.weather;

/**
 * Temperature Unit Model
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class TemperatureUnitModel {

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
