package com.ignis.android_cleanarchitecture.presentation.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.FragmentMainBinding;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ProfileViewModel;
import com.ignis.android_cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel;
import com.ignis.android_cleanarchitecture.presentation.view.adapter.ProfileRecyclerAdapter;

import java.util.Collections;

/**
 * Main Fragment
 *
 * @author Kensuke
 */
public class MainFragment extends Fragment implements MainFragmentListener {

    private FragmentMainBinding binding;
    private MainFragmentViewModel viewModel;
    private ProfileRecyclerAdapter adapter;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
            viewModel = new MainFragmentViewModel(getActivity(), this);
            binding.setViewModel(viewModel);

            adapter = new ProfileRecyclerAdapter(getActivity());
            binding.recyclerName.setAdapter(adapter);
        }
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.onStart();
    }

    @Override
    public void onStop() {
        viewModel.onStop();
        super.onStop();
    }

    /* MainFragment.MainFragmentListener */

    @Override
    public void onGetWeather(ProfileViewModel profile) {
        adapter.setProfileViewModelList(Collections.singletonList(profile));
        adapter.notifyDataSetChanged();
    }

}
