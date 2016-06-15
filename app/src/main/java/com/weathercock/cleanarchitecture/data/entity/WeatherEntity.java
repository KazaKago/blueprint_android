package com.weathercock.cleanarchitecture.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Weather Entity
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class WeatherEntity extends RealmObject {

    //地域ID
    @PrimaryKey
    private int cityId;

    //予報を発表した地域を定義
    private LocationEntity location;
    //タイトル・見出し
    private String title;
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    private String link;
    //予報の発表日時
    private String publicTime;
    //天気概況文
    private DescriptionEntity description;
    //府県天気予報の予報日毎の配列
    private RealmList<ForecastEntity> forecasts;
    //ピンポイント予報の発表地点の配列
    private RealmList<LinkEntity> pinpointLocations;
    //コピーライト
    private CopyrightEntity copyright;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public DescriptionEntity getDescription() {
        return description;
    }

    public void setDescription(DescriptionEntity description) {
        this.description = description;
    }

    public RealmList<ForecastEntity> getForecasts() {
        return forecasts;
    }

    public void setForecasts(RealmList<ForecastEntity> forecasts) {
        this.forecasts = forecasts;
    }

    public RealmList<LinkEntity> getPinpointLocations() {
        return pinpointLocations;
    }

    public void setPinpointLocations(RealmList<LinkEntity> pinpointLocations) {
        this.pinpointLocations = pinpointLocations;
    }

    public CopyrightEntity getCopyright() {
        return copyright;
    }

    public void setCopyright(CopyrightEntity copyright) {
        this.copyright = copyright;
    }

}
