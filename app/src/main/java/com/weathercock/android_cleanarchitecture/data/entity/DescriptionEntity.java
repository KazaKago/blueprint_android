package com.weathercock.android_cleanarchitecture.data.entity;

import io.realm.RealmObject;

/**
 * Description Entity
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class DescriptionEntity extends RealmObject {

    //天気概況文
    private String text;
    //天気概況文の発表時刻
    private String publicTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

}
