package com.ignis.android_cleanarchitecture.domain.model;

import io.realm.RealmObject;

/**
 * Temperature Model
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class TemperatureModel extends RealmObject {

    //最高気温
    private TemperatureUnitModel max;
    //最低気温
    private TemperatureUnitModel min;

    public TemperatureUnitModel getMax() {
        return max;
    }

    public void setMax(TemperatureUnitModel max) {
        this.max = max;
    }

    public TemperatureUnitModel getMin() {
        return min;
    }

    public void setMin(TemperatureUnitModel min) {
        this.min = min;
    }

}
