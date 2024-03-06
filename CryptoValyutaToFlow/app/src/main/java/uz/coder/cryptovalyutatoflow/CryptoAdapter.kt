package uz.coder.cryptovalyutatoflow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.coder.cryptovalyutatoflow.databinding.CryptoItemBinding
import uz.coder.cryptovalyutatoflow.model.Coin


class CryptoAdapter : ListAdapter<Coin, CryptoAdapter.CryptoViewHolder>(CryptoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val binding = CryptoItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val context = holder.binding.root.context
        val currency = getItem(position)
        holder.binding.textViewCurrencyName.text = currency.name
        holder.binding.textViewCurrencyPrice.text = context.getString(
            R.string.currency_price, //$%s
            "${currency.prise}"
        )
    }

    class CryptoViewHolder(val binding: CryptoItemBinding) : RecyclerView.ViewHolder(binding.root)
}

private object CryptoDiffCallback : DiffUtil.ItemCallback<Coin>() {
    override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
        return oldItem == newItem
    }
}