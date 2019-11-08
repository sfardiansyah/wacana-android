package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository

import android.util.Log
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.data.TransactionDao
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Transaction

class TransactionRepository private constructor(private val transactionDao: TransactionDao) {

    fun insert(cartItems: List<Cart>) {
        val count = transactionDao.getCount()
        val transaction = Transaction(
            count+1,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
        transactionDao.insert(transaction)
        Log.d("insertTransaction", "Trx ID: " + transaction.id + ". Cart Items: " + cartItems.map {
            it.book.title + " -> " + it.count
        })
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TransactionRepository? = null

        fun getInstance(transactionDao: TransactionDao) =
            instance ?: synchronized(this) {
                instance ?: TransactionRepository(transactionDao).also { instance = it }
            }
    }
}