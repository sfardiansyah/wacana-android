package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.shoppingCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.TransactionRepository

class ShoppingCartViewModel(cartRepository: CartRepository, private val transactionRepository: TransactionRepository) : ViewModel() {

    private val _navigateToHomePage= MutableLiveData<List<Cart>>()

    val navigateToHomePage: LiveData<List<Cart>> = _navigateToHomePage

    val cartItems: LiveData<List<Cart>> = cartRepository.getCartItems()

    fun checkout(cartItems: List<Cart>) {
        transactionRepository.insert(cartItems)
        _navigateToHomePage.value = cartItems
    }

    fun doneNavigating() {
        _navigateToHomePage.value = null
    }

    external fun calculateTotalPrice(cartItems: LongArray): Long

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}