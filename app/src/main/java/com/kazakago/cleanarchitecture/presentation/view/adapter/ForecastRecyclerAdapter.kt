package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
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

    data class ViewHolder(val binding: RecyclerForecastBinding) : RecyclerView.ViewHolder(binding.root)

    var forecastList: List<ForecastViewModel> = ArrayList()
    var listener: ForecastRecyclerAdapterListener? = null

    override fun getItemCount(): Int = forecastList.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val binding = RecyclerForecastBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = forecastList[position]

        holder?.binding?.setVariable(BR.viewModel, item)
        holder?.binding?.executePendingBindings()
        holder?.binding?.root?.setOnClickListener {
            listener?.onItemClick(item)
        }
    }

}
