package com.weathercock.cleanarchitecture.data.entity;

import io.realm.RealmObject;

/**
 * Temperature Entity
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class TemperatureEntity extends RealmObject {

    //最高気温
    private TemperatureUnitEntity max;
    //最低気温
    private TemperatureUnitEntity min;

    public TemperatureUnitEntity getMax() {
        return max;
    }

    public void setMax(TemperatureUnitEntity max) {
        this.max = max;
    }

    public TemperatureUnitEntity getMin() {
        return min;
    }

    public void setMin(TemperatureUnitEntity min) {
        this.min = min;
    }

}
