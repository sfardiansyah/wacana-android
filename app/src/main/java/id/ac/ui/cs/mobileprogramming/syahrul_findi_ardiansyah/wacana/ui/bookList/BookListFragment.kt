package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentBookListBinding
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.home.HomeFragmentDirections
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.utilities.InjectorUtils

class BookListFragment: Fragment() {

    companion object {
        const val TAG = "BookListFragment"
    }

    private val bookListViewModel: BookListViewModel by viewModels {
        InjectorUtils.provideBookListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_list, container, false)

        val adapter = BookListAdapter(BookListener { book ->
            bookListViewModel.onBookClicked(book)
        })

        binding.bookList.adapter = adapter
        binding.sectionTitle.text = getString(R.string.title_new_section)
        binding.sectionSubtitle.text = getString(R.string.subtitle_new_section)

        setupNavigation()

        bookListViewModel.books.observe(this, Observer {
            adapter.submitList(it)
        })

        binding.bookList.layoutManager = LinearLayoutManager(
            this.requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return binding.container
    }

    private fun setupNavigation() {
        bookListViewModel.navigateToBookDetail.observe(this, Observer { book ->
            book?.let {
                this.findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToBookDetailFragment(book.id))
                bookListViewModel.doneNavigating()
            }
        })
    }
}