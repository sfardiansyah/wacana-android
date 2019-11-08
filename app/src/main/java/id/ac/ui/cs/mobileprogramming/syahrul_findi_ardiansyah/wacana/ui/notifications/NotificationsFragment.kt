package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentNotificationsBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Cart
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils

class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by viewModels {
        InjectorUtils.provideNotificationsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNotificationsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_notifications, container, false
        )

        notificationsViewModel.text.observe(this, Observer {
            binding.textNotifications.text = it
        })

        setupNavigation()

        notificationsViewModel.cartItems.observe(this, Observer {
            it?.let{
                binding.textTotalPrice.text = notificationsViewModel.calculateTotalPrice(it).toString()
                binding.clickListener = CheckoutListener {
                    notificationsViewModel.checkout(it)
                }
            }
        })

        return binding.root
    }

    private fun setupNavigation() {
        notificationsViewModel.navigateToHomePage.observe(this, Observer { cartItems ->
            cartItems?.let {
                Log.d("setupNavigation", "mashoook")
                Toast.makeText(requireContext(), "Transaction created!", Toast.LENGTH_SHORT).show()
                this.findNavController().popBackStack()
                notificationsViewModel.doneNavigating()
            }
        })
    }
}

class CheckoutListener(val clickListener: () -> Unit) {
    fun onClick() = clickListener()
}