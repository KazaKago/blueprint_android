package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.domain.usecase.AboutUseCase;

import javax.inject.Inject;

/**
 * About Fragment ViewModel
 *
 * @author Kensuke
 */
public class AboutFragmentViewModel {

    @Inject
    public AboutUseCase aboutUseCase;

    private Context context;
    public ObservableField<String> verText;
    public ObservableField<String> developByText;
    public ObservableField<String> copyrightText;

    public AboutFragmentViewModel(Context context) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.verText = new ObservableField<>(context.getString(R.string.about_ver, aboutUseCase.getCurrentVersion()));
        this.developByText = new ObservableField<>(context.getString(R.string.about_develop_by, context.getString(R.string.developer_name)));
        this.copyrightText = new ObservableField<>(context.getString(R.string.about_copyright, aboutUseCase.getCurrentYear(), context.getString(R.string.developer_name)));
    }

    public void onClickPlayStore(View view) {
        toPlayStore();
    }

    public void onClickMail(View view) {
        toMailApp();
    }

    public void onClickWebSite(View view) {
        toWebSite();
    }

    /**
     * PlayStoreへ遷移
     */
    private void toPlayStore() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutUseCase.getPlayStoreUrl()));
        context.startActivity(intent);
    }

    /**
     * メールアプリを開く
     */
    private void toMailApp() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + aboutUseCase.getMailUrl()));
        context.startActivity(intent);
    }

    /**
     * Webサイトをブラウザで開く
     */
    private void toWebSite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(aboutUseCase.getWebSiteUrl()));
        context.startActivity(intent);
    }

}
