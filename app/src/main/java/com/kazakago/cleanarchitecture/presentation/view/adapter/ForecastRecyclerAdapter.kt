package com.kazakago.cleanarchitecture.presentation.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kazakago.cleanarchitecture.BR
import com.kazakago.cleanarchitecture.R
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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding: RecyclerForecastBinding = DataBindingUtil.bind<RecyclerForecastBinding>(itemView)
    }

    var forecastList: List<ForecastViewModel> = ArrayList()
    var listener: ForecastRecyclerAdapterListener? = null

    override fun getItemCount(): Int {
        return forecastList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_forecast, parent, false)
        return ForecastRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForecastRecyclerAdapter.ViewHolder, position: Int) {
        val item = forecastList[position]

        holder.binding.setVariable(BR.viewModel, item)
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener {
            listener?.onItemClick(item)
        }
    }

}
