package com.ignis.android_cleanarchitecture.presentation.presenter.adapter;

import android.content.Context;
import android.databinding.ObservableField;

import com.ignis.android_cleanarchitecture.domain.model.city.CityModel;

/**
 * City ViewModel
 * <p>
 * Created by tamura_k on 2016/06/01.
 */
public class CityViewModel {

    public ObservableField<Integer> id;
    public ObservableField<String> name;

    private Context context;

    public CityViewModel(Context context, CityModel cityModel){
        this.context = context;
        this.id = new ObservableField<>(cityModel.getId());
        this.name = new ObservableField<>(cityModel.getName());
    }

}
