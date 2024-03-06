package www.uzmd.das.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.uzmd.das.KitobModell
import www.uzmd.das.UstozModell
import www.uzmd.das.databinding.ItemDarslarBinding

class MyAdapterDarslar(var list: List<KitobModell>) : RecyclerView.Adapter<MyAdapterDarslar.VH>() {
    inner class VH(var itemDarslarBinding: ItemDarslarBinding) :
        RecyclerView.ViewHolder(itemDarslarBinding.root) {
        fun binding(u: KitobModell, pozition: Int) {
            var text = "${u.fanMavzu}\n\n${u.fanMazmun}"
            itemDarslarBinding.shwOutputText.setText(text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemDarslarBinding.inflate(LayoutInflater.from(parent.context), null, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding(list[position], position)
    }
}