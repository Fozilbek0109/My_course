package uz.coder.d2lesson128caroutin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.coder.d2lesson128caroutin.R
import uz.coder.d2lesson128caroutin.databinding.ItemUserBinding
import uz.coder.d2lesson128caroutin.model.User

class AdapterRc(val list: List<User>):RecyclerView.Adapter<AdapterRc.VH>() {

    inner  class  VH( val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
     holder.binding.apply {
         name.text = list[position].name
         userName.text = list[position].username
         compName.text = list[position].company?.name
         email.text = list[position].email
         phone.text = list[position].phone
         cite.text = list[position].address?.city
         prise.text = list[position].company?.catchPhrase
         website.text = list[position].website

     }
    }


}