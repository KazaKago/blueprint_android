package com.ignis.android_cleanarchitecture.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.databinding.RecyclerWeatherBinding;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Weather RecyclerView Adapter
 * <p/>
 * Created by tamura_k on 2016/05/31.
 */
public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerWeatherBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(itemView);
        }

        public RecyclerWeatherBinding getBinding() {
            return binding;
        }

    }

    private Context context;
    private List<WeatherViewModel> weatherViewModelList;

    public WeatherRecyclerAdapter(Context context) {
        this.context = context;
        this.weatherViewModelList = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return weatherViewModelList.size();
    }

    @Override
    public WeatherRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_weather, parent, false);
        return new WeatherRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherRecyclerAdapter.ViewHolder holder, int position) {
        final WeatherViewModel item = weatherViewModelList.get(position);

        holder.getBinding().setVariable(com.ignis.android_cleanarchitecture.BR.viewModel, item);
        holder.getBinding().executePendingBindings();
    }

    public void setWeatherViewModelList(List<WeatherViewModel> weatherViewModelList) {
        this.weatherViewModelList = weatherViewModelList;
    }

}
