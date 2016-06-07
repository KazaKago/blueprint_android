package com.ignis.android_cleanarchitecture.data.entity;

import io.realm.RealmObject;

/**
 * Forecasts Entity
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class ForecastEntity extends RealmObject {

    //予報日
    private String date;
    //予報日(今日、明日、明後日のいずれか)
    private String dateLabel;
    //天気（晴れ、曇り、雨など）
    private String telop;
    //画像
    private ImageEntity image;
    //気温
    private TemperatureEntity temperature;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public String getTelop() {
        return telop;
    }

    public void setTelop(String telop) {
        this.telop = telop;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public TemperatureEntity getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureEntity temperature) {
        this.temperature = temperature;
    }

}
