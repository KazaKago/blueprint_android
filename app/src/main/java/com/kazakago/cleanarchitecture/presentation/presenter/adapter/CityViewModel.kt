package com.kazakago.cleanarchitecture.presentation.presenter.adapter

import android.content.Context
import android.databinding.ObservableField

import com.kazakago.cleanarchitecture.domain.model.city.CityModel

/**
 * City ViewModel
 *
 * Created by tamura_k on 2016/06/01.
 */
class CityViewModel(private val context: Context, cityModel: CityModel) {

    val id = ObservableField<String>(cityModel.id)
    val name = ObservableField<String>(cityModel.name)

}
