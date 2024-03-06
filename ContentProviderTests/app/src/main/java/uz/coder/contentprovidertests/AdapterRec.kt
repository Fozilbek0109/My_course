package uz.coder.contentprovidertests

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coder.contentprovidertests.databinding.ItemViewEnabledBinding

class AdapterRec(private val list: List<ShopItem>):RecyclerView.Adapter<AdapterRec.VH>() {

    inner class VH(val binding: ItemViewEnabledBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = VH(
        ItemViewEnabledBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.id1.text = list[position].count.toString()
        holder.binding.name.text = list[position].name
    }
}