package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository

class NotificationsViewModel(cartRepository: CartRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val cartItems: LiveData<List<Cart>> = cartRepository.getCartItems()

    fun calculateTotalPrice(cartItems: List<Cart>) = cartItems.sumBy {
        (it.book.price * it.count).toInt()
    }
}