package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository

class CartViewModelFactory(
    private val cartRepository: CartRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(cartRepository) as T
    }
}