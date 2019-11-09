package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.CartDao
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.TransactionDao
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart

class CartRepository private constructor(
    private val cartDao: CartDao,
    private val transactionDao: TransactionDao
){

    fun insert(book: Book) {
        val count = transactionDao.getCount()
        try {
            cartDao.insert(Cart(book, count + 1))
        } catch ( exception : SQLiteConstraintException) {
            val selectedCart = cartDao.getCart(book.id)
            cartDao.updateCartCount(selectedCart.id, selectedCart.count + 1)
        }
    }

    fun getCart(bookId: Int) = cartDao.getCart(bookId)

    fun getCartItems(): LiveData<List<Cart>> {
        val count = transactionDao.getCount()
        return cartDao.getCartItems(count + 1)
    }

    fun updateCartCount(cartId: Int, count: Int) {
        if (count > 0) {
            cartDao.updateCartCount(cartId, count)
        } else {
            cartDao.deleteCartItem(cartId, transactionDao.getCount() + 1)
        }
    }

    fun deleteAllCartItems() = cartDao.deleteAllCartItems()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: CartRepository? = null

        fun getInstance(cartDao: CartDao, transactionDao: TransactionDao) =
            instance ?: synchronized(this) {
                instance ?: CartRepository(cartDao, transactionDao).also { instance = it }
            }
    }
}
