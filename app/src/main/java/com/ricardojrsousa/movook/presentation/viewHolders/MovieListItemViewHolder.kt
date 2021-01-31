package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieListItemViewHolder : BindableViewHolder<Movie> {

    override lateinit var itemView: View

    override fun bind(movie: Movie?, clickListener: ((view: ImageView?, t: Movie?) -> Unit)?) {
        itemView.credit_movie_title.text = movie?.originalTitle
        itemView.credit_movie_year.text = movie?.getReleaseYear()
        if (movie?.voteAverage != null) {
            itemView.credit_rating.setVote(movie.voteAverage)
        }

        itemView.setOnClickListener { clickListener?.invoke(null, movie) }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Movie> {
        itemView = layoutInflater.inflate(R.layout.list_item_movie, container, false)
        return this
    }
}