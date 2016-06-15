package com.weathercock.cleanarchitecture.presentation.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weathercock.cleanarchitecture.R;
import com.weathercock.cleanarchitecture.databinding.FragmentAboutBinding;
import com.weathercock.cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel;

/**
 * About Fragment
 *
 * @author Kensuke
 */
public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;
    private AboutFragmentViewModel viewModel;

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new AboutFragmentViewModel(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
            binding.setViewModel(viewModel);
        }
        return binding.getRoot();
    }

}
