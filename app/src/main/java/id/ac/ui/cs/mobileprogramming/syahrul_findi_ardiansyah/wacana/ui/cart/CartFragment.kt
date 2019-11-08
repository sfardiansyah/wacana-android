package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentCartListBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils

class CartFragment: Fragment() {

    private val bookListViewModel: CartViewModel by viewModels {
        InjectorUtils.provideCartViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCartListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cart_list, container, false
        )

        val adapter = CartAdapter(CartListener({ cartItem ->
            Log.d("Increment", cartItem.book.title)
        }, { cartItem ->
            Log.d("Decrement", cartItem.book.title)
        }))

        binding.cartList.adapter = adapter

        bookListViewModel.cartItems.observe(this, Observer {
            adapter.submitList(it)
        })

        binding.cartList.layoutManager = LinearLayoutManager(
            this.requireContext(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
}