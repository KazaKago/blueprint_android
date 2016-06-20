package com.kazakago.cleanarchitecture.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.kazakago.cleanarchitecture.R;
import com.kazakago.cleanarchitecture.databinding.ActivityMainBinding;
import com.kazakago.cleanarchitecture.presentation.listener.view.fragment.MainFragmentListener;
import com.kazakago.cleanarchitecture.presentation.presenter.activity.MainActivityViewModel;
import com.kazakago.cleanarchitecture.presentation.view.fragment.MainFragment;

/**
 * Main Activity
 *
 * @author Kensuke
 */
public class MainActivity extends AppCompatActivity implements MainFragmentListener {

    private MainActivityViewModel viewModel;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new MainActivityViewModel(this);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);

        if (savedInstanceState == null) {
            replaceMainFragment();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                viewModel.onClickAboutMenu(item);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void replaceMainFragment() {
        MainFragment fragment = MainFragment.newInstance();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_fragment, fragment);
        fragmentTransaction.commit();
    }

    /* MainFragmentListener */

    @Override
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
