package com.kazakago.cleanarchitecture.data.entity.city;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Pref Entity.
 * <p>
 * Created by weath on 2016/06/14.
 */
public class PrefEntity {

    @SerializedName("title")
    private String title;
    @SerializedName("city")
    private List<CityEntity> cityList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CityEntity> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityEntity> cityList) {
        this.cityList = cityList;
    }

}
