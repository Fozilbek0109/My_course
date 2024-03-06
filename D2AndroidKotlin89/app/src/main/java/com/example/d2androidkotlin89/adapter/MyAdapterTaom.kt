package com.example.d2androidkotlin89.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.d2androidkotlin89.databinding.ItemTaomBinding
import com.example.d2androidkotlin89.model.TaomModel

class MyAdapterTaom(val list:List<TaomModel>) : RecyclerView.Adapter<MyAdapterTaom.VH>() {
    inner class VH(var itemTaomBinding: ItemTaomBinding) :
        RecyclerView.ViewHolder(itemTaomBinding.root){
            fun bind(taom:TaomModel,position: Int){
                itemTaomBinding.name.text = taom.name
                itemTaomBinding.price.text = taom.price
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemTaomBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position],position)
    }
}