package com.ricardojrsousa.movook.presentation.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieListItemView : BindableViewHolder<Movie> {

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