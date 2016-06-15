package com.weathercock.cleanarchitecture.data.entity.weather;

import io.realm.RealmObject;

/**
 * Link Entity
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class LinkEntity extends RealmObject {

    //市区町村名
    private String name;
    //対応するlivedoor 天気情報のURL
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
