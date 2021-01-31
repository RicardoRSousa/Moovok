package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.wrappers.loadMovieBackdrop
import kotlinx.android.synthetic.main.list_item_top_rated_movie.view.*

/**
 * Created by Ricardo Sousa on 30/01/2021.
 */
class TopRatedMovieViewHolder : BindableViewHolder<Movie> {

    override lateinit var itemView: View

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Movie> {
        itemView = layoutInflater.inflate(R.layout.list_item_top_rated_movie, container, false)
        return this
    }

    override fun bind(movie: Movie?, clickListener: ((view: ImageView?, t: Movie?) -> Unit)?) {
        with(itemView) {
            top_rated_movie_backdrop.loadMovieBackdrop(movie?.backdropPath)
            top_rated_year.text = movie?.getReleaseYear()
            top_rated_vote_view.setVote(movie?.voteAverage)
            top_rated_movie_title.text = movie?.title
        }
        itemView.setOnClickListener { clickListener?.invoke(null, movie) }
    }
}