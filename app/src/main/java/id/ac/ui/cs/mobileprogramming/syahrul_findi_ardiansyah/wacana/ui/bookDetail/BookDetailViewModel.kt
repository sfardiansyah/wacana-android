package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.BookRepository

class BookDetailViewModel(bookRepository: BookRepository, private val bookId: Int): ViewModel() {

    val book = bookRepository.getBook(bookId)
}
