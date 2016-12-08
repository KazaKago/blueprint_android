package com.kazakago.cleanarchitecture.presentation.presenter.activity;

import android.content.Context;
import android.view.MenuItem;

import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.MainActivityViewModelListener;

/**
 * Main Activity ViewModel
 *
 * @author Kensuke
 */
public class MainActivityViewModel {

    private Context context;
    private MainActivityViewModelListener listener;

    public MainActivityViewModel(Context context, MainActivityViewModelListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void onClickAboutMenu(MenuItem item) {
        listener.toAboutActivity();
    }

}
