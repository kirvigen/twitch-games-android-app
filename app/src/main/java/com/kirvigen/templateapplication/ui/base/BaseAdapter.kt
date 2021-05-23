package com.kirvigen.templateapplication.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(val data:MutableList<T>): RecyclerView.Adapter<BaseViewHolder>() {

    abstract fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return getHolder(parent,viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position]!!)
    }

    override fun getItemCount() = data.size
}