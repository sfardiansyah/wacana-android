package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentBookDetailBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.model.Book
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils

class BookDetailFragment : Fragment() {

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

        bookDetailViewModel.book.observe(this, Observer {
            it?.let {
                binding.book = it
                binding.bookCover.setImageResource(it.imgSrc)
                binding.clickListener = AddToCartListener {
                    if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                requireActivity(),
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        ) {
                            Snackbar.make(
                                binding.container,
                                "Read external storage permission is needed to add this book to the shopping cart.",
                                Snackbar.LENGTH_INDEFINITE
                            ).setAction("OK") {
                                ActivityCompat.requestPermissions(
                                    requireActivity(),
                                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                                    100
                                )
                            }.show()
                        } else {
                            ActivityCompat.requestPermissions(
                                requireActivity(),
                                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                                100
                            )
                        }
                    } else {
                        bookDetailViewModel.onCTAClicked(it)
                    }
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
                this.findNavController().navigate(
                    BookDetailFragmentDirections.actionBookDetailFragmentToNavigationNotifications(
                        book.id
                    )
                )
                bookDetailViewModel.doneNavigating()
            }
        })
    }
}

class AddToCartListener(val clickListener: (book: Book) -> Unit) {
    fun onClick(book: Book) = clickListener(book)
}