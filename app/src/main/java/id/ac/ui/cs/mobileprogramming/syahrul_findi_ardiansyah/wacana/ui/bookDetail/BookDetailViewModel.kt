package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.BookRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository

class BookDetailViewModel(bookRepository: BookRepository, private val cartRepository: CartRepository, private val bookId: Int): ViewModel() {

    private val _navigateToShoppingCart = MutableLiveData<Book>()

    val book = bookRepository.getBook(bookId)

    val navigateToShoppingCart: LiveData<Book> = _navigateToShoppingCart

    fun onCTAClicked(book: Book) {
        cartRepository.insert(book)
        _navigateToShoppingCart.value = book
    }

    fun doneNavigating() {
        _navigateToShoppingCart.value = null
    }
}
