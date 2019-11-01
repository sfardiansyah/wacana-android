package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.BookRepository

class BookListViewModel(bookRepository: BookRepository): ViewModel() {

    private val _navigateToBookDetail = MutableLiveData<Book>()

    val books: LiveData<List<Book>> = bookRepository.getAllBooks()

    val navigateToBookDetail: LiveData<Book> = _navigateToBookDetail

    fun onBookClicked(book: Book) {
        _navigateToBookDetail.value = book
    }

    fun doneNavigating() {
        _navigateToBookDetail.value = null
    }
}