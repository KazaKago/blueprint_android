package com.ignis.android_cleanarchitecture.presentation.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.FragmentAboutBinding;
import com.ignis.android_cleanarchitecture.presentation.presenter.fragment.AboutFragmentViewModel;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about, container, false);
            viewModel = new AboutFragmentViewModel(getActivity());
            binding.setViewModel(viewModel);
        }
        return binding.getRoot();
    }

}
