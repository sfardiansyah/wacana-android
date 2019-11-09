package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository

class CartViewModel(private val cartRepository: CartRepository): ViewModel() {

    val cartItems: LiveData<List<Cart>> = cartRepository.getCartItems()

    fun incrementCount(item: Cart) {
        cartRepository.updateCartCount(item.id, item.count + 1)
    }

    fun decrementCount(item: Cart) {
        cartRepository.updateCartCount(item.id, item.count - 1)
    }
}