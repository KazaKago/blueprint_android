package com.ignis.android_cleanarchitecture.presentation.listener.fragment;

import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ProfileViewModel;

import java.util.List;

/**
 * Main Fragment Listener
 * <p>
 * Created by tamura_k on 2016/06/02.
 */
public interface MainFragmentListener {

    void onGetProfileList(List<ProfileViewModel> profileList);

}
