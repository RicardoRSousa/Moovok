package com.ricardojrsousa.movook.presentation.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.wrappers.loadBookCover
import kotlinx.android.synthetic.main.list_item_book.view.*

class BookCoverView : BindableViewHolder<Book> {

    override lateinit var itemView: View

    override fun bind(book: Book?, position: Int?, clickListener: ((view: ImageView?, t: Book?) -> Unit)?) {
        with(itemView.book_cover) {
            loadBookCover(book?.volumeInfo?.imageLinks?.thumbnail)
            transitionName = book?.id
        }
        itemView.setOnClickListener { clickListener?.invoke(itemView.book_cover, book) }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Book> {
        itemView = layoutInflater.inflate(R.layout.list_item_book, container, false)
        return this
    }
}