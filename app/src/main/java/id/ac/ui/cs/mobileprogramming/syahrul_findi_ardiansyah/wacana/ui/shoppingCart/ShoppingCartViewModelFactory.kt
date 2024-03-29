package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.shoppingCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.CartRepository
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.repository.TransactionRepository

class ShoppingCartViewModelFactory(
    private val cartRepository: CartRepository,
    private val transactionRepository: TransactionRepository
): ViewModelProvider.NewInstanceFactory() {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingCartViewModel(cartRepository, transactionRepository) as T
        }
}