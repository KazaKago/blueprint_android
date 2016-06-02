package com.ignis.android_cleanarchitecture.presentation.presenter.activity;

import android.content.Context;

import com.ignis.android_cleanarchitecture.presentation.listener.activity.AboutActivityListener;

/**
 * About Activity ViewModel
 *
 * @author Kensuke
 */
public class AboutActivityViewModel {

    private Context context;
    private AboutActivityListener listener;

    public AboutActivityViewModel(Context context, AboutActivityListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void onClickBackIcon() {
        listener.onPerformFinish();
    }

}
