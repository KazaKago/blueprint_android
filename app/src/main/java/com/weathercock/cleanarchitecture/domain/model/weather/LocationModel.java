package com.weathercock.cleanarchitecture.domain.model.weather;

/**
 * Location Model
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class LocationModel {

    //地方名（例・九州地方）
    private String area;
    //都道府県名（例・福岡県）
    private String prefecture;
    //1次細分区名（例・八幡）
    private String city;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String pref) {
        this.prefecture = pref;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
