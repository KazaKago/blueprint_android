package com.weathercock.cleanarchitecture.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Copyright Entity
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class CopyrightEntity extends RealmObject {

    //コピーライトの文言
    private String title;
    //livedoor 天気情報のURL
    private String link;
    //livedoor 天気情報へのURL、アイコンなど
    private ImageEntity image;
    //livedoor 天気情報で使用している気象データの配信元
    private RealmList<LinkEntity> provider;

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

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public RealmList<LinkEntity> getProvider() {
        return provider;
    }

    public void setProvider(RealmList<LinkEntity> provider) {
        this.provider = provider;
    }

}
