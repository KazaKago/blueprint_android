package com.ignis.android_cleanarchitecture.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.ActivityAboutBinding;
import com.ignis.android_cleanarchitecture.presentation.listener.activity.AboutActivityListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.activity.AboutActivityViewModel;
import com.ignis.android_cleanarchitecture.presentation.view.fragment.AboutFragment;

/**
 * About Activity
 *
 * @author Kensuke
 */
public class AboutActivity extends AppCompatActivity implements AboutActivityListener {

    private AboutActivityViewModel viewModel;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        viewModel = new AboutActivityViewModel(this, this);
        binding.setViewModel(viewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            replaceAboutFragment();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                viewModel.onClickBackIcon();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceAboutFragment() {
        AboutFragment fragment = AboutFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragment);
        fragmentTransaction.commit();
    }

    /* AboutActivity.AboutActivityListener */

    @Override
    public void onPerformFinish() {
        finish();
    }

}
