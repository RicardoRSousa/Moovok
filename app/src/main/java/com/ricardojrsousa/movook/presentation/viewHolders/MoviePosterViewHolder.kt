package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import kotlinx.android.synthetic.main.list_item_movie_poster.view.*

class MoviePosterViewHolder(private val posterHeight: Int? = null, private val posterWidth: Int? = null) : BindableViewHolder<Movie> {

    override lateinit var itemView: View

    override fun bind(movie: Movie?, position: Int?, clickListener: ((view: ImageView?, t: Movie?) -> Unit)?) {
        itemView.movie_poster_view.initView(movie, clickListener, posterHeight, posterWidth)
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Movie> {
        itemView = layoutInflater.inflate(R.layout.list_item_movie_poster, container, false)
        return this
    }

}