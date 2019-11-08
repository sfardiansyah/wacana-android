package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(item: Cart)

    @Query("SELECT * FROM carts WHERE id = :bookId")
    fun getCart(bookId: Int): Cart

    @Query("SELECT * FROM carts WHERE transactionId = :transactionId")
    fun getCartItems(transactionId: Int): LiveData<List<Cart>>

    @Query("UPDATE carts SET count = :count WHERE id = :cartId")
    fun updateCartCount(cartId: Int, count: Int)

    @Query("DELETE FROM carts")
    fun deleteAllCartItems()
}