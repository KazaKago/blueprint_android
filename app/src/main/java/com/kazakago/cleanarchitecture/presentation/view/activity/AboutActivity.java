package com.kazakago.cleanarchitecture.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kazakago.cleanarchitecture.R;
import com.kazakago.cleanarchitecture.databinding.ActivityAboutBinding;
import com.kazakago.cleanarchitecture.presentation.listener.presenter.activity.AboutActivityViewModelListener;
import com.kazakago.cleanarchitecture.presentation.presenter.activity.AboutActivityViewModel;
import com.kazakago.cleanarchitecture.presentation.view.fragment.AboutFragment;

/**
 * About Activity
 *
 * @author Kensuke
 */
public class AboutActivity extends AppCompatActivity implements AboutActivityViewModelListener {

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

        setSupportActionBar(binding.toolbar);
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

    /* AboutActivityViewModelListener */

    @Override
    public void onPerformFinish() {
        finish();
    }

}
