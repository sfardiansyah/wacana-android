package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.BookRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository

class BookDetailViewModelFactory(
    private val bookRepository: BookRepository,
    private val cartRepository: CartRepository,
    private val bookId: Int
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BookDetailViewModel(bookRepository, cartRepository, bookId) as T
    }
}