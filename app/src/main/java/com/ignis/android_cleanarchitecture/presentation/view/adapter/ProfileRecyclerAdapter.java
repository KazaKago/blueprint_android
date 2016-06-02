package com.ignis.android_cleanarchitecture.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.RecyclerNameBinding;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ProfileViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Name RecyclerView Adapter
 * <p/>
 * Created by tamura_k on 2016/05/31.
 */
public class ProfileRecyclerAdapter extends RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerNameBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(itemView);
        }

        public RecyclerNameBinding getBinding() {
            return binding;
        }

    }

    private Context context;
    private List<ProfileViewModel> profileViewModelList;

    public ProfileRecyclerAdapter(Context context) {
        this.context = context;
        this.profileViewModelList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return profileViewModelList.size();
    }

    @Override
    public ProfileRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_name, parent, false);
        return new ProfileRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileRecyclerAdapter.ViewHolder holder, int position) {
        final ProfileViewModel item = profileViewModelList.get(position);

        holder.getBinding().setVariable(com.ignis.android_cleanarchitecture.BR.viewModel, item);
        holder.getBinding().executePendingBindings();
    }

    public List<ProfileViewModel> getProfileViewModelList() {
        return profileViewModelList;
    }

    public void setProfileViewModelList(List<ProfileViewModel> profileViewModelList) {
        this.profileViewModelList = profileViewModelList;
    }

}
