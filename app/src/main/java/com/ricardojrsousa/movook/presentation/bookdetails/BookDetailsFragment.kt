package com.ricardojrsousa.movook.presentation.bookdetails

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.wrappers.loadBookCover
import com.squareup.picasso.Callback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_book_details.*
import kotlinx.android.synthetic.main.fragment_book_details.view.*

@AndroidEntryPoint
class BookDetailsFragment : BaseFragment<BookDetailsViewModel>(R.layout.fragment_book_details) {

    override val viewModel: BookDetailsViewModel by viewModels()

    private val args: BookDetailsFragmentArgs by navArgs()

    private lateinit var bookId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bookId = args.bookId
        viewModel.init(bookId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel(view)
    }

    private fun setupView(view: View, book: Book) {
        val volume = book.volumeInfo
        with(view) {
            book_title.text = volume.title
            book_cover_image.transitionName = book.id
            book_cover_image.loadBookCover(volume.imageLinks?.thumbnail, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }
            })
            book_author.text = volume.getAuthors()
            book_year.text = volume.publishedDate
            if (volume.description != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    book_description.text = Html.fromHtml(volume.description, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    book_description.text = Html.fromHtml(volume.description)
                }
            }
            book_page_count.text = volume.pageCount.toString()
            book_categories.text = volume.getCategories()
            book_isbn13.text = volume.getIsbn13()
            book_publisher.text = volume.publisher
            book_rating.setVote(volume.averageRating)
        }
    }

    private fun observeViewModel(view: View) {
        viewModel.bookDetails.observe(viewLifecycleOwner, {
            setupView(view, it)
        })
    }
}