package uz.coder.d2lesson114retrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coder.d2lesson114retrofit.R
import uz.coder.d2lesson114retrofit.databinding.ItemCurrencyBinding
import uz.coder.d2lesson114retrofit.model.CurrencyModel

class CurrencyAdapterRec(val list: List<CurrencyModel>,val context: Context) :RecyclerView.Adapter<CurrencyAdapterRec.VH>() {
    inner class VH(val binding: ItemCurrencyBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.apply {
            txtValyuta.text = String.format(context.getString(R.string.valuta,list[position].ccyNmUZ,list[position].rate))
            txtFarq.text = String.format(context.getString(R.string.farq,list[position].diff))
            txtDate.text = String.format(context.getString(R.string.vaqt,list[position].date))
        }
    }

}