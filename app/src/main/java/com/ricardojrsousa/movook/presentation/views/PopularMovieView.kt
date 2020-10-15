package com.ricardojrsousa.movook.presentation.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import kotlinx.android.synthetic.main.list_item_popular_movie.view.*

/**
 * Created by Ricardo Sousa on 15/10/2020
 */
class PopularMovieView : BindableViewHolder<Movie> {

    override lateinit var itemView: View

    override fun bind(movie: Movie?, position: Int?, clickListener: ((view: ImageView?, t: Movie?) -> Unit)?) {
        with(itemView) {

            popular_movie_rank.text = position?.plus(1).toString()

            popular_movie_poster_image.transitionName = movie?.id
            popular_movie_poster_image.loadMoviePoster(movie?.posterPath)

            popular_movie_title.text = movie?.title
            popular_movie_year.text = movie?.getReleaseYear()
            popular_movie_vote_view.setVote(movie?.voteAverage)
        }

        itemView.setOnClickListener {
            //itemView.movie_poster.transitionName = movie?.id
            //clickListener?.invoke(itemView.movie_poster, movie)
        }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Movie> {
        itemView = layoutInflater.inflate(R.layout.list_item_popular_movie, container, false)
        return this
    }
}