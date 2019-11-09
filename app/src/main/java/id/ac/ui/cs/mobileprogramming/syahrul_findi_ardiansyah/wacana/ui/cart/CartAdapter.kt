package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentCartItemBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart

class CartAdapter(private val clickListener: CartListener): ListAdapter<Cart, CartAdapter.CartHolder>(CartDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class CartHolder private constructor(private val binding: FragmentCartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cart, clickListener: CartListener){
            binding.cart = item
            binding.bookCover.setImageResource(item.book.imgSrc)
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): CartHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentCartItemBinding.inflate(layoutInflater, parent, false)
                return CartHolder(binding)
            }
        }
    }
}

class CartDiffCallback: DiffUtil.ItemCallback<Cart>(){
    override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
        return oldItem == newItem
    }
}

class CartListener(val incrementListener: (item: Cart) -> Unit, val decrementListener: (item: Cart) -> Unit) {
    fun onClickIncrement(item: Cart) = incrementListener(item)
    fun onClickDecrement(item: Cart) = decrementListener(item)
}