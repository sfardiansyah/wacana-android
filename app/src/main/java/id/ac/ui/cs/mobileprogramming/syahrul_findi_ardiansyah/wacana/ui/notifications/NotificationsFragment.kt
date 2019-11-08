package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
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
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        val textTotalPrice: TextView = root.findViewById(R.id.text_total_price)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })

        notificationsViewModel.cartItems.observe(this, Observer {
            textTotalPrice.text = notificationsViewModel.calculateTotalPrice(it).toString()
        })

        return root
    }
}