package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.wrappers.loadBigMoviePoster
import kotlinx.android.synthetic.main.list_item_movie_suggestion.view.*

/**
 * Created by Ricardo Sousa on 26/01/2021.
 */
class MovieSuggestionViewHolder : BindableViewHolder<Movie> {

    override lateinit var itemView: View

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Movie> {
        itemView = layoutInflater.inflate(R.layout.list_item_movie_suggestion, container, false)
        return this
    }

    override fun bind(movie: Movie?, clickListener: ((view: ImageView?, t: Movie?) -> Unit)?) {
        with(itemView.movie_suggestion_cover) {
            loadBigMoviePoster(movie?.posterPath, cornerRadius = 10f)
            transitionName = movie?.id
        }

        with(itemView) {
            movie_suggestion_title.text = movie?.title
            movie_suggestion_desc.text = movie?.overview
            movie_suggestion_vote_view.setVote(movie?.voteAverage)
            movie_suggestion_year.text = movie?.getReleaseYear()
        }

        itemView.setOnClickListener { clickListener?.invoke(itemView.movie_suggestion_cover, movie) }
    }

}