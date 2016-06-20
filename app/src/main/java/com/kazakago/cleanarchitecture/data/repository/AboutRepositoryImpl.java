package com.kazakago.cleanarchitecture.data.repository;

import android.content.Context;

import com.kazakago.cleanarchitecture.R;
import com.kazakago.cleanarchitecture.data.util.StoreUtils;
import com.kazakago.cleanarchitecture.data.util.VersionUtils;
import com.kazakago.cleanarchitecture.domain.repository.AboutRepository;

/**
 * About Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class AboutRepositoryImpl implements AboutRepository {

    private Context context;

    public AboutRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public String getPlayStoreUrl() {
        return StoreUtils.getStoreAppLink(context);
    }

    @Override
    public String getMailUrl() {
        return context.getString(R.string.developer_mail_address);
    }

    @Override
    public String getWebSiteUrl() {
        return context.getString(R.string.developer_website_url);
    }

    @Override
    public String getCurrentVersion() {
        return VersionUtils.getVersionName(context);
    }

}
