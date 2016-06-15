package com.weathercock.cleanarchitecture.data.entity;

import io.realm.RealmObject;

/**
 * Image Entity
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class ImageEntity extends RealmObject {

    //天気（晴れ、曇り、雨など）
    private String title;
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    private String link;
    //天気アイコンのURL
    private String url;
    //天気アイコンの幅
    private Integer width;
    //天気アイコンの高さ
    private Integer height;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
