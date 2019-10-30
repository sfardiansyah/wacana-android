package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import java.text.NumberFormat
import java.util.*

class BookListAdapter(private val books: List<Book>, private val onBookListener: OnBookListener): RecyclerView.Adapter<BookListAdapter.BookHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_book_card, parent, false)

        return BookHolder(v, onBookListener)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val book = books[position]
        var formatRp = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        formatRp.maximumFractionDigits = 0

        holder.bookTitle.text = book.title
        holder.bookAuthor.text = book.author
        holder.bookPrice.text = formatRp.format(book.price)
    }

    class BookHolder(view: View, onBookListener: OnBookListener): RecyclerView.ViewHolder(view), View.OnClickListener {
        var bookTitle: TextView = view.findViewById(R.id.book_title)
        var bookAuthor: TextView = view.findViewById(R.id.book_author)
        var bookPrice: TextView = view.findViewById(R.id.book_price)
        var listener: OnBookListener = onBookListener

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.onBookClick(adapterPosition)
        }
    }

    interface OnBookListener {
        fun onBookClick(position: Int)
    }
}