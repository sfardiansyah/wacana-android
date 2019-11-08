package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository

import android.database.sqlite.SQLiteConstraintException
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.CartDao
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart

class CartRepository private constructor(private val cartDao: CartDao){

    fun insert(book: Book) {
        try {
            cartDao.insert(Cart(book))
        } catch ( exception : SQLiteConstraintException) {
            val selectedCart = cartDao.getCart(book.id)
            cartDao.updateCartCount(selectedCart.id, selectedCart.count + 1)
        }
    }

    fun getCart(bookId: Int) = cartDao.getCart(bookId)

    fun getCartItems() = cartDao.getCartItems()

    fun updateCartCount(cartId: Int, count: Int) = cartDao.updateCartCount(cartId, count)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CartRepository? = null

        fun getInstance(cartDao: CartDao) =
            instance ?: synchronized(this) {
                instance ?: CartRepository(cartDao).also { instance = it }
            }
    }
}