package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.shoppingCart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentShoppingCartBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.IndonesiaCurrency
import java.math.BigDecimal

class ShoppingCartFragment : Fragment() {

    private val shoppingCartViewModel: ShoppingCartViewModel by viewModels {
        InjectorUtils.provideNotificationsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoppingCartBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shopping_cart, container, false
        )

        setupNavigation()

        shoppingCartViewModel.cartItems.observe(this, Observer {
            it?.let{
                binding.textTotalPrice.text = IndonesiaCurrency.valueOf(BigDecimal(shoppingCartViewModel.calculateTotalPrice(it)))
                binding.clickListener = CheckoutListener {
                    shoppingCartViewModel.checkout(it)
                }
            }
        })

        binding.priceTitle.text = getString(R.string.title_price)

        return binding.root
    }

    private fun setupNavigation() {
        shoppingCartViewModel.navigateToHomePage.observe(this, Observer { cartItems ->
            cartItems?.let {
                Toast.makeText(requireContext(), "Transaction created!", Toast.LENGTH_SHORT).show()
                this.findNavController().popBackStack()
                shoppingCartViewModel.doneNavigating()
            }
        })
    }
}

class CheckoutListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}