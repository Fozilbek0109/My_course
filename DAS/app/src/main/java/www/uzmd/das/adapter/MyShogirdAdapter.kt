package www.uzmd.das.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import www.uzmd.das.UstozModell
import www.uzmd.das.databinding.ItemShogirdBinding

class MyShogirdAdapter(val list: List<UstozModell>) :
    RecyclerView.Adapter<MyShogirdAdapter.VH>() {
    inner class VH(var itemShogirdBinding: ItemShogirdBinding) :
        RecyclerView.ViewHolder(itemShogirdBinding.root) {
        fun binding(u: UstozModell, position: Int) {
            itemShogirdBinding.fanName.setText(u.fanNomi)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemShogirdBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding(list[position], position)
    }
}