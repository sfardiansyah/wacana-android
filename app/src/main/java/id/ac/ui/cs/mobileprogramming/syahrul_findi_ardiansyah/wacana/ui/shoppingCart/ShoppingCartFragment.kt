package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.shoppingCart

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
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
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.IndonesiaCurrency
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.ReminderManager
import java.math.BigDecimal


class ShoppingCartFragment : Fragment() {

    private lateinit var connManager: ConnectivityManager

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

        connManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        setupNavigation()

        shoppingCartViewModel.cartItems.observe(this, Observer {
            it?.let{
                val priceList = it.map {cart ->
                    cart.book.price
                }

                binding.textTotalPrice.text = IndonesiaCurrency.valueOf(BigDecimal(shoppingCartViewModel.calculateTotalPrice(priceList.toLongArray())))
                binding.clickListener = CheckoutListener {
                    val mWifi = connManager.getNetworkCapabilities(connManager.activeNetwork)

                    if (mWifi != null && mWifi.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        shoppingCartViewModel.checkout(it)
                        ReminderManager.cancelReminder(requireContext().applicationContext)
                    } else {
                        Toast.makeText(requireContext(), "You have to connect with WiFi to proceed checkout", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

        binding.priceTitle.text = getString(R.string.title_price)

        return binding.container
    }

    private fun setupNavigation() {
        shoppingCartViewModel.navigateToHomePage.observe(this, Observer { cartItems ->
            cartItems?.let {
                this.findNavController().navigate(ShoppingCartFragmentDirections.actionNavigationNotificationsToLoadingActivity())
                shoppingCartViewModel.doneNavigating()
            }
        })
    }
}

class CheckoutListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}