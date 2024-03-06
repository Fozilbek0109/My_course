package com.example.d2androidkotlin89.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.d2androidkotlin89.databinding.ItemCategoryBinding
import com.example.d2androidkotlin89.model.CategoryModel

class MyAdapterCategory(val list:List<CategoryModel>,var onClickItem:(id:Int) -> Unit) : RecyclerView.Adapter<MyAdapterCategory.VH>()
{

    inner class VH(var itemCategoryBinding: ItemCategoryBinding):
        RecyclerView.ViewHolder(itemCategoryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemCategoryBinding.txt.text = list[position].name
        holder.itemView.setOnClickListener{
            onClickItem.invoke(list[position].id)
        }
    }
}