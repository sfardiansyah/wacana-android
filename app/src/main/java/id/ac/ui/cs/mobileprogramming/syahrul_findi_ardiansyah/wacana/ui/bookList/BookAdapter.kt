package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentBookCardBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book

class BookAdapter(private val clickListener: BookListener): ListAdapter<Book, BookAdapter.BookHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        return BookHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class BookHolder private constructor(private val binding: FragmentBookCardBinding) : RecyclerView.ViewHolder(binding.container) {
        fun bind(item: Book, clickListener: BookListener){
            binding.book = item
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): BookHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentBookCardBinding.inflate(layoutInflater, parent, false)
                return BookHolder(binding)
            }
        }
    }

}

class BookDiffCallback: DiffUtil.ItemCallback<Book>(){
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}


class BookListener(val clickListener: (book: Book) -> Unit) {
    fun onClick(book: Book) = clickListener(book)
}
