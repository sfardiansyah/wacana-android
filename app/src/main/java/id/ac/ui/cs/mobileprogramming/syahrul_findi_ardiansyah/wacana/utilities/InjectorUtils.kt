package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities

import android.content.Context
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.WacanaDatabase
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.BookRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail.BookDetailViewModelFactory
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList.BookListViewModelFactory
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.cart.CartViewModelFactory
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.notifications.NotificationsViewModelFactory

object InjectorUtils {

    private fun getBookRepository(context: Context): BookRepository {
        return BookRepository.getInstance(
            WacanaDatabase.getInstance(context.applicationContext).bookDao())
    }

    fun getCartRepository(context: Context): CartRepository {
        return CartRepository.getInstance(
            WacanaDatabase.getInstance(context.applicationContext).cartDao()
        )
    }

    fun provideBookListViewModelFactory(context: Context): BookListViewModelFactory {
        return BookListViewModelFactory(getBookRepository(context))
    }

    fun provideBookDetailViewModelFactory(
        context: Context,
        bookId: Int
    ): BookDetailViewModelFactory {
        return BookDetailViewModelFactory(getBookRepository(context), getCartRepository(context), bookId)
    }

    fun provideCartViewModelFactory(
        context: Context
    ): CartViewModelFactory {
        return CartViewModelFactory(getCartRepository(context))
    }

    fun provideNotificationsViewModelFactory(
        context: Context
    ): NotificationsViewModelFactory {
        return NotificationsViewModelFactory((getCartRepository(context)))
    }
}