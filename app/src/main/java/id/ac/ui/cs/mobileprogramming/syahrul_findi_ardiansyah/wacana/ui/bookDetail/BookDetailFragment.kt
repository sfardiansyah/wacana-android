package id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.ui.bookDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.R
import id.ac.ui.cs.mobileprogramming.syahrul_findi_ardiansyah.wacana.databinding.FragmentBookDetailBinding
import java.text.NumberFormat
import java.util.*

class BookDetailFragment: Fragment() {

    private lateinit var bookDetailViewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBookDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_book_detail, container, false
        )

        val args: BookDetailFragmentArgs by navArgs()

        bookDetailViewModel = ViewModelProviders.of(this)[BookDetailViewModel::class.java]

//        var formatRp = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
//        formatRp.maximumFractionDigits = 0

        bookDetailViewModel.book.observe(this, Observer {
            binding.book = it
        })

        bookDetailViewModel.getDetail(args.bookTitle)

        binding.lifecycleOwner = this

        return binding.root
    }
}