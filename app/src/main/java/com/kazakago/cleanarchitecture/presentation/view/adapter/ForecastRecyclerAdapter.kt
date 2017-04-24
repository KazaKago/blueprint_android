package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.BR
import com.kazakago.cleanarchitecture.databinding.RecyclerForecastBinding
import com.kazakago.cleanarchitecture.presentation.listener.view.adapter.ForecastRecyclerAdapterListener
import com.kazakago.cleanarchitecture.presentation.presenter.adapter.ForecastViewModel
import java.util.*

/**
 * Forecast RecyclerView Adapter
 *
 * Created by tamura_k on 2016/05/31.
 */
class ForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<ForecastRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: RecyclerForecastBinding = DataBindingUtil.bind<RecyclerForecastBinding>(itemView)
    }

    var forecastViewModelList: List<ForecastViewModel> = ArrayList()
    var forecastRecyclerAdapterListener: ForecastRecyclerAdapterListener? = null

    override fun getItemCount(): Int {
        return forecastViewModelList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastRecyclerAdapter.ViewHolder {
        val binding = RecyclerForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ForecastRecyclerAdapter.ViewHolder, position: Int) {
        val item = forecastViewModelList[position]

        holder.binding.setVariable(BR.viewModel, item)
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener {
            forecastRecyclerAdapterListener?.onItemClick(item)
        }
    }

}
