package com.kazakago.cleanarchitecture.presentation.presenter.activity;

import android.content.Context;

import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.AboutActivityViewModelListener;

/**
 * About Activity ViewModel
 *
 * @author Kensuke
 */
public class AboutActivityViewModel {

    private Context context;
    private AboutActivityViewModelListener aboutActivityViewModelListener;

    public AboutActivityViewModel(Context context, AboutActivityViewModelListener aboutActivityViewModelListener) {
        this.context = context;
        this.aboutActivityViewModelListener = aboutActivityViewModelListener;
    }

    public void onClickBackIcon() {
        aboutActivityViewModelListener.onPerformFinish();
    }

}
