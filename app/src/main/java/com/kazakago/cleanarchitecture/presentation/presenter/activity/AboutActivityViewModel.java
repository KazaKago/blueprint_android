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
    private AboutActivityViewModelListener listener;

    public AboutActivityViewModel(Context context, AboutActivityViewModelListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void onClickBackIcon() {
        listener.onPerformFinish();
    }

}
