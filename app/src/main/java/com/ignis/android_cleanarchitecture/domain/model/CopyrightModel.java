package com.ignis.android_cleanarchitecture.domain.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Copyright Model
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class CopyrightModel extends RealmObject {

    //コピーライトの文言
    private String title;
    //livedoor 天気情報のURL
    private String link;
    //livedoor 天気情報へのURL、アイコンなど
    private ImageModel image;
    //livedoor 天気情報で使用している気象データの配信元
    private RealmList<LinkModel> provider;

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

    public ImageModel getImage() {
        return image;
    }

    public void setImage(ImageModel image) {
        this.image = image;
    }

    public RealmList<LinkModel> getProvider() {
        return provider;
    }

    public void setProvider(RealmList<LinkModel> provider) {
        this.provider = provider;
    }

}
