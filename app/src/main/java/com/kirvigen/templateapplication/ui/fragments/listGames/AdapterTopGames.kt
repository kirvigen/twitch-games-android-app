package com.kirvigen.templateapplication.ui.fragments.listGames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kirvigen.templateapplication.R
import com.kirvigen.templateapplication.data.models.database.TopGameDb
import com.kirvigen.templateapplication.ui.base.BaseAdapter
import com.kirvigen.templateapplication.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_top_game.view.*

class AdapterTopGames(data:MutableList<Any>):BaseAdapter<Any>(data) {

    fun setData(newData:List<Any>){
        if(data.isNotEmpty()){
            val start = data.size
            data.addAll(newData)
            notifyItemRangeInserted(start,newData.size)
        }else{
            data.addAll(newData)
            notifyDataSetChanged()
        }
    }

    override fun getHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            else ->{
                val v = LayoutInflater.from(parent.context).inflate(R.layout.item_top_game,parent,false)
                TopGameViewHolder(v)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is TopGameDb -> 0
            else -> 0
        }
    }

    class TopGameViewHolder(view: View):BaseViewHolder(view){

        override fun bind(obj: Any) {
            if(obj !is TopGameDb) return
            Glide.with(itemView.image).load(obj.image).into(itemView.image)
            itemView.name.text = obj.name
            itemView.views.text = obj.viewers.toString()
            itemView.channels.text = obj.channels.toString()
        }
    }
}