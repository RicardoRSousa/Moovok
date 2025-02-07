package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewListAdapter
import kotlinx.android.synthetic.main.view_known_for.view.*

class KnownForViewHolder : BindableViewHolder<List<Movie>> {
    override lateinit var itemView: View

    override fun bind(movies: List<Movie>?, clickListener: ((view: ImageView?, t: List<Movie>?) -> Unit)?) {
        if (movies != null) {
            val movieListAdapter = BindableViewListAdapter(MoviePosterViewHolder(150, 100)) { view, movie ->
                clickListener?.invoke(view, listOf(movie!!))
            }.also { it.addItems(movies) }

            with(itemView.known_for_list) {
                adapter = movieListAdapter
                layoutManager = GridLayoutManager(itemView.context, 3, GridLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<List<Movie>> {
        itemView = layoutInflater.inflate(R.layout.view_known_for, container, false)
        return this
    }
}