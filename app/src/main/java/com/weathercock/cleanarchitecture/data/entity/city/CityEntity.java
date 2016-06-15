package com.weathercock.cleanarchitecture.data.entity.city;

import com.google.gson.annotations.SerializedName;

/**
 * City Entity.
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class CityEntity {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
