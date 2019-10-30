package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book

class BookListViewModel: ViewModel() {

    private val _books = MutableLiveData<List<Book>>().apply {
        value = listOf(
            Book("Hahaha", "Haha", 100000),
            Book("Hehehe", "Hehe", 110000),
            Book("Huhuhu", "Huhu", 120000),
            Book("Hohoho", "Hoho", 130000)
        )
    }
    private val _navigateToBookDetail = MutableLiveData<Book>()

    val books: LiveData<List<Book>> = _books
    val navigateToBookDetail: LiveData<Book> = _navigateToBookDetail

    fun onBookClicked(book: Book) {
        _navigateToBookDetail.value = book
    }

    fun doneNavigating() {
        _navigateToBookDetail.value = null
    }
}