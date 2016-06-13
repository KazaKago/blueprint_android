package com.weathercock.android_cleanarchitecture.presentation.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weathercock.android_cleanarchitecture.R;
import com.weathercock.android_cleanarchitecture.databinding.FragmentMainBinding;
import com.weathercock.android_cleanarchitecture.presentation.listener.presenter.fragment.MainFragmentViewModelListener;
import com.weathercock.android_cleanarchitecture.presentation.listener.view.fragment.MainFragmentListener;
import com.weathercock.android_cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel;

/**
 * Main Fragment
 *
 * @author Kensuke
 */
public class MainFragment extends Fragment implements MainFragmentViewModelListener {

    private FragmentMainBinding binding;
    private MainFragmentViewModel viewModel;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MainFragmentViewModel(getActivity(), this);
        viewModel.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
            binding.setViewModel(viewModel);
        }
        viewModel.onCreateView(inflater, container, savedInstanceState);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewModel.onSaveInstanceState(outState);
    }

    /* MainFragmentViewModelListener */

    @Override
    public void setActionBarTitle(String title) {
        mainFragmentListener.setActionBarTitle(title);
    }

    @Override
    public void setCitySpinnerSelection(int position) {
        binding.citySpinner.setSelection(position);
    }

}
