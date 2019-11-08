package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.TransactionRepository

class NotificationsViewModel(cartRepository: CartRepository, private val transactionRepository: TransactionRepository) : ViewModel() {

    private val _navigateToHomePage= MutableLiveData<List<Cart>>()

    val navigateToHomePage: LiveData<List<Cart>> = _navigateToHomePage

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val cartItems: LiveData<List<Cart>> = cartRepository.getCartItems()

    fun calculateTotalPrice(cartItems: List<Cart>) = cartItems.sumBy {
        (it.book.price * it.count).toInt()
    }

    fun checkout(cartItems: List<Cart>) {
        transactionRepository.insert(cartItems)
        _navigateToHomePage.value = cartItems
    }

    fun doneNavigating() {
        _navigateToHomePage.value = null
    }
}