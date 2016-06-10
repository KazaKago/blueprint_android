package com.ignis.android_cleanarchitecture.presentation.view.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.FragmentMainBinding;
import com.ignis.android_cleanarchitecture.presentation.listener.activity.MainActivityListener;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.fragment.MainFragmentViewModel;

/**
 * Main Fragment
 *
 * @author Kensuke
 */
public class MainFragment extends Fragment implements MainFragmentListener {

    private FragmentMainBinding binding;
    private MainFragmentViewModel viewModel;
    private MainActivityListener mainActivityListener;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mainActivityListener = (MainActivityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + MainActivityListener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
            viewModel = new MainFragmentViewModel(getActivity(), this);
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

    /* MainFragment.MainFragmentListener */

    @Override
    public void setActionBarTitle(String title) {
        mainActivityListener.setActionBarTitle(title);
    }

}
