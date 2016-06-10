package com.weathercock.android_cleanarchitecture.presentation.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weathercock.android_cleanarchitecture.R;
import com.weathercock.android_cleanarchitecture.databinding.FragmentMainBinding;
import com.weathercock.android_cleanarchitecture.presentation.listener.view.fragment.MainFragmentListener;
import com.weathercock.android_cleanarchitecture.presentation.listener.presenter.fragment.MainFragmentViewModelListener;

/**
 * Main Fragment
 *
 * @author Kensuke
 */
public class MainFragment extends Fragment implements MainFragmentViewModelListener {

    private FragmentMainBinding binding;
    private com.weathercock.android_cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel viewModel;
    private MainFragmentListener mainFragmentListener;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mainFragmentListener = (MainFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + MainFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
            viewModel = new com.weathercock.android_cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel(getActivity(), this);
            binding.setViewModel(viewModel);
        }
        viewModel.onCreateView(savedInstanceState);
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

    /* MainFragmentViewModelListener */

    @Override
    public void setActionBarTitle(String title) {
        mainFragmentListener.setActionBarTitle(title);
    }

}
