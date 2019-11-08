package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentBookDetailBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils

class BookDetailFragment: Fragment() {

    private val args: BookDetailFragmentArgs by navArgs()

    private val bookDetailViewModel: BookDetailViewModel by viewModels {
        InjectorUtils.provideBookDetailViewModelFactory(requireContext(), args.bookId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_detail, container, false
        )

//        var formatRp = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//        formatRp.maximumFractionDigits = 0

        bookDetailViewModel.book.observe(this, Observer {
            it?.let {
                binding.book = it
                binding.clickListener = AddToCartListener {
                    bookDetailViewModel.onCTAClicked(it)
                }
            }
        })

        setupNavigation()

        binding.lifecycleOwner = this

        return binding.container
    }

    private fun setupNavigation() {
        bookDetailViewModel.navigateToShoppingCart.observe(this, Observer { book ->
            book?.let {
                this.findNavController().navigate(BookDetailFragmentDirections.actionBookDetailFragmentToNavigationNotifications(book.id))
                bookDetailViewModel.doneNavigating()
            }
        })
    }
}

class AddToCartListener(val clickListener: (book: Book) -> Unit) {
    fun onClick(book: Book) = clickListener(book)
}