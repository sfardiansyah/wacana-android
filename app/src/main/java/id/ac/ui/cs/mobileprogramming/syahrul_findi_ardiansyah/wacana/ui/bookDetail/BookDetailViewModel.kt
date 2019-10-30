package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book

class BookDetailViewModel: ViewModel() {
    private val _book = MutableLiveData<Book>().apply {
        value = Book("Hahaha", "Haha", 100000)
    }

    val book: LiveData<Book> = _book
}