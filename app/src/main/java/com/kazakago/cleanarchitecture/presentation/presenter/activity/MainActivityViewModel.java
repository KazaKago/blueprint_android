package com.kazakago.cleanarchitecture.presentation.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.kazakago.cleanarchitecture.presentation.view.activity.AboutActivity;

/**
 * Main Activity ViewModel
 *
 * @author Kensuke
 */
public class MainActivityViewModel {

    private Context context;

    public MainActivityViewModel(Context context) {
        this.context = context;
    }

    public void onClickAboutMenu(MenuItem item) {
        toAboutActivity();
    }

    private void toAboutActivity() {
        Intent intent = AboutActivity.newInstance(context);
        context.startActivity(intent);
    }

}
