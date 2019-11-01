package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities

import android.content.Context
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.WacanaDatabase
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.BookRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail.BookDetailViewModelFactory
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList.BookListViewModelFactory

object InjectorUtils {

    private fun getBookRepository(context: Context): BookRepository {
        return BookRepository.getInstance(
            WacanaDatabase.getInstance(context.applicationContext).bookDao())
    }

    fun provideBookListViewModelFactory(context: Context): BookListViewModelFactory {
        return BookListViewModelFactory(getBookRepository(context))
    }

    fun provideBookDetailViewModelFactory(
        context: Context,
        bookId: Int
    ): BookDetailViewModelFactory {
        return BookDetailViewModelFactory(getBookRepository(context), bookId)
    }
}