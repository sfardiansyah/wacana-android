package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import java.text.NumberFormat
import java.util.*

class BookDetailFragment: Fragment() {

    private lateinit var bookDetailViewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookDetailViewModel = ViewModelProviders.of(this)[BookDetailViewModel::class.java]
        var root =  inflater.inflate(R.layout.fragment_book_detail, container, false)
        var bookTitle: TextView = root.findViewById(R.id.book_title)
        var bookAuthor: TextView = root.findViewById(R.id.book_author)
        var bookPrice: TextView = root.findViewById(R.id.book_price)

        var formatRp = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        formatRp.maximumFractionDigits = 0

        bookDetailViewModel.book.observe(this, Observer {
            bookTitle.text = it.title
            bookAuthor.text = it.author
            bookPrice.text = formatRp.format(it.price)
        })

        return root
    }
}