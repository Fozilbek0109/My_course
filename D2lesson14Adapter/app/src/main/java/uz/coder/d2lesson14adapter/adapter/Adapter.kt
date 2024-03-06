package uz.coder.d2lesson14adapter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coder.d2lesson14adapter.databinding.ItemBookBinding
import uz.coder.d2lesson14adapter.model.BookModel

class Adapter(
    var list: MutableList<BookModel>,
    val bookDelit: (BookModel, Int) -> Unit,
    val bookEdit: (BookModel, Int) -> Unit

) : RecyclerView.Adapter<Adapter.VH>() {
    inner class VH(var itemBookBinding: ItemBookBinding) :
        RecyclerView.ViewHolder(itemBookBinding.root) {
        fun bind(b: BookModel,position: Int) {
            itemBookBinding.apply {
                name.text = b.name
                author.text = b.author
            }
            itemBookBinding.delete.setOnClickListener{
                bookDelit.invoke(b,position)
            }
            itemBookBinding.edit.setOnClickListener{
                bookEdit.invoke(b,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemBookBinding.inflate(LayoutInflater.from(parent.context), null, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(list[position],position)
    }
}