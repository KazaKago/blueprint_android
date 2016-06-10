package com.weathercock.android_cleanarchitecture.presentation.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.weathercock.android_cleanarchitecture.R;
import com.weathercock.android_cleanarchitecture.databinding.SpinnerCityBinding;
import com.weathercock.android_cleanarchitecture.databinding.SpinnerCityDropdownBinding;
import com.weathercock.android_cleanarchitecture.presentation.presenter.adapter.CityViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * City Spinner Adapter
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class CitySpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<CityViewModel> cityViewModelList;

    public CitySpinnerAdapter(Context context) {
        this.context = context;
        this.cityViewModelList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return cityViewModelList.size();
    }

    @Override
    public CityViewModel getItem(int position) {
        return cityViewModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id.get();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SpinnerCityBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.spinner_city, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (SpinnerCityBinding) convertView.getTag();
        }

        binding.setViewModel(getItem(position));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        SpinnerCityDropdownBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.spinner_city_dropdown, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (SpinnerCityDropdownBinding) convertView.getTag();
        }

        binding.setViewModel(getItem(position));
        return convertView;
    }

    public void setCityViewModelList(List<CityViewModel> cityViewModelList) {
        this.cityViewModelList = cityViewModelList;
    }

}
