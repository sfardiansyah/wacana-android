package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail.BookDetailFragment

class BookListFragment: Fragment(), BookListAdapter.OnBookListener {

    companion object {
        const val TAG = "BookListFragment";
    }

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookListViewModel = ViewModelProviders.of(this)[BookListViewModel::class.java]
        setupNavigation()

        val root = inflater.inflate(R.layout.fragment_book_list, container, false)

        val linearLayoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.book_list)
        recyclerView.layoutManager = linearLayoutManager

        bookListViewModel.books.observe(this, Observer {
            recyclerView.adapter = BookListAdapter(it, this)
        })

        return root
    }

    private fun setupNavigation() {
        bookListViewModel.navigateToBookDetail.observe(this, Observer { book ->
            book?.let {
                this.findNavController().navigate(R.id.fragment2)
                bookListViewModel.doneNavigating()
            }
        })
    }

    override fun onBookClick(position: Int) {
        Log.d(TAG, "onBookClick: clicked."+position)
    }
}