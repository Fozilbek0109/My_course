package www.uzmd.das.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.uzmd.das.databinding.ItemUstozBinding
import www.uzmd.das.UstozModell

class MyAdapterUstoz(
    var list: ArrayList<UstozModell>,
    var onDelete: (UstozModell, Int) -> Unit,
    var onEdit: (UstozModell, Int) -> Unit,
    var onClickDarslar: (model:UstozModell,id:Int) -> Unit
) :
    RecyclerView.Adapter<MyAdapterUstoz.VH>() {
    inner class VH(var itemUstozBinfing: ItemUstozBinding) :
        RecyclerView.ViewHolder(itemUstozBinfing.root) {
        fun binding(u: UstozModell, position: Int) {

            itemUstozBinfing.fanName.setText(u.fanNomi)

            itemUstozBinfing.deletImg.setOnClickListener {
                onDelete(u, position)
            }
            itemUstozBinfing.editImg.setOnClickListener {
                onEdit(u, position)
            }
            itemUstozBinfing.ustozClickLy.setOnClickListener {
                // bu yerda invok metodiga listimizda pozitionda turdan model id si jo'natildi
                // avvalgida invok ishlatilmagan ekan
                onClickDarslar.invoke(list[position],list[position].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUstozBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding(list[position], position)
    }
}