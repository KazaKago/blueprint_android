package com.ignis.android_cleanarchitecture.domain.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Weather Model
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class WeatherModel extends RealmObject {

    //地域ID
    @PrimaryKey
    private int cityId;

    //予報を発表した地域を定義
    private LocationModel location;
    //タイトル・見出し
    private String title;
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    private String link;
    //予報の発表日時
    private String publicTime;
    //天気概況文
    private DescriptionModel description;
    //府県天気予報の予報日毎の配列
    private RealmList<ForecastsModel> forecasts;
    //ピンポイント予報の発表地点の配列
    private RealmList<LinkModel> pinpointLocation;
    //コピーライト
    private CopyrightModel copyright;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }


    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
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

    public DescriptionModel getDescription() {
        return description;
    }

    public void setDescription(DescriptionModel description) {
        this.description = description;
    }

    public RealmList<ForecastsModel> getForecasts() {
        return forecasts;
    }

    public void setForecasts(RealmList<ForecastsModel> forecasts) {
        this.forecasts = forecasts;
    }

    public RealmList<LinkModel> getPinpointLocation() {
        return pinpointLocation;
    }

    public void setPinpointLocation(RealmList<LinkModel> pinpointLocation) {
        this.pinpointLocation = pinpointLocation;
    }

    public CopyrightModel getCopyright() {
        return copyright;
    }

    public void setCopyright(CopyrightModel copyright) {
        this.copyright = copyright;
    }

}
