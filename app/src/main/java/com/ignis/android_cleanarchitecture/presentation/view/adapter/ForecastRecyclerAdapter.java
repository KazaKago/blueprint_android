package com.ignis.android_cleanarchitecture.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.RecyclerForecastBinding;
import com.ignis.android_cleanarchitecture.presentation.listener.view.adapter.ForecastRecyclerAdapterListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ForecastViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Forecast RecyclerView Adapter
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class ForecastRecyclerAdapter extends RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerForecastBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(itemView);
        }

        public RecyclerForecastBinding getBinding() {
            return binding;
        }

    }

    private Context context;
    private List<ForecastViewModel> forecastViewModelList;
    private ForecastRecyclerAdapterListener forecastRecyclerAdapterListener;

    public ForecastRecyclerAdapter(Context context) {
        this.context = context;
        this.forecastViewModelList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return forecastViewModelList.size();
    }

    @Override
    public ForecastRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_forecast, parent, false);
        return new ForecastRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastRecyclerAdapter.ViewHolder holder, int position) {
        final ForecastViewModel item = forecastViewModelList.get(position);

        holder.getBinding().setVariable(com.ignis.android_cleanarchitecture.BR.viewModel, item);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().setOnClickListener(v -> {
            if (forecastRecyclerAdapterListener != null) forecastRecyclerAdapterListener.onItemClick(item);
        });
        item.loadImage(holder.getBinding().weatherImage);
    }

    public void setForecastViewModelList(List<ForecastViewModel> forecastViewModelList) {
        this.forecastViewModelList = forecastViewModelList;
    }

    public void setForecastRecyclerAdapterListener(ForecastRecyclerAdapterListener forecastRecyclerAdapterListener) {
        this.forecastRecyclerAdapterListener = forecastRecyclerAdapterListener;
    }

}
