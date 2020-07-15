package com.ricardojrsousa.movook.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Book
import com.ricardojrsousa.movook.wrappers.loadBookCover
import kotlinx.android.synthetic.main.book_list_item.view.*

/**
 * Created by ricardosousa on 23/03/2020
 */
class BookListAdapter(
    private val onBookClickListener: ((view: ImageView, book: Book) -> Unit)? = null
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    private var items: ArrayList<Book> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = items[position]
        holder.bind(book)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun addItems(books: List<Book>) {
        items.addAll(books)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        internal fun bind(book: Book) {
            with(view.book_cover) {
                loadBookCover(book.volumeInfo.imageLinks?.thumbnail)
                transitionName = book.volumeInfo.imageLinks?.thumbnail
            }
            view.setOnClickListener { onBookClickListener?.invoke(view.book_cover, book) }
        }
    }
}