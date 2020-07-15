package com.ricardojrsousa.movook.presentation.adapters.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieView(layoutInflater: LayoutInflater, container: ViewGroup?) : BindableViewHolder<Movie> {

    override val itemView: View = layoutInflater.inflate(R.layout.movie_list_item, container, false)

    override fun bind(movie: Movie?, clickListener: ((view: ImageView, t: Movie?) -> Unit)?) {
        with(itemView.movie_poster) {
            loadMoviePoster(movie?.posterPath)
            transitionName = movie?.id.toString()
        }
        itemView.setOnClickListener { clickListener?.invoke(itemView.movie_poster, movie) }
    }
}