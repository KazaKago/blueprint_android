package com.ignis.android_cleanarchitecture.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.RecyclerPinpointLocationBinding;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.PinpointLocationViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Name RecyclerView Adapter
 * <p/>
 * Created by tamura_k on 2016/05/31.
 */
public class PinpointLocationRecyclerAdapter extends RecyclerView.Adapter<PinpointLocationRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerPinpointLocationBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(itemView);
        }

        public RecyclerPinpointLocationBinding getBinding() {
            return binding;
        }

    }

    private Context context;
    private List<PinpointLocationViewModel> pinpointLocationViewModelList;

    public PinpointLocationRecyclerAdapter(Context context) {
        this.context = context;
        this.pinpointLocationViewModelList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return pinpointLocationViewModelList.size();
    }

    @Override
    public PinpointLocationRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_pinpoint_location, parent, false);
        return new PinpointLocationRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PinpointLocationRecyclerAdapter.ViewHolder holder, int position) {
        final PinpointLocationViewModel item = pinpointLocationViewModelList.get(position);

        holder.getBinding().setVariable(com.ignis.android_cleanarchitecture.BR.viewModel, item);
        holder.getBinding().executePendingBindings();
    }

    public List<PinpointLocationViewModel> getPinpointLocationViewModelList() {
        return pinpointLocationViewModelList;
    }

    public void setPinpointLocationViewModelList(List<PinpointLocationViewModel> pinpointLocationViewModelList) {
        this.pinpointLocationViewModelList = pinpointLocationViewModelList;
    }

}
