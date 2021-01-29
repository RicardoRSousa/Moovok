package com.ricardojrsousa.movook.presentation.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.utils.dp
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import com.wajahatkarim3.easyflipview.EasyFlipView
import kotlinx.android.synthetic.main.view_movie_back.view.*
import kotlinx.android.synthetic.main.view_movie_front.view.*
import kotlinx.android.synthetic.main.view_movie_poster.view.*

/**
 * Created by Ricardo Sousa on 19/10/2020
 */
class MoviePosterView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private var movie: Movie? = null

    init {
        inflate(context, R.layout.view_movie_poster, this)
    }

    fun initView(
        movie: Movie?, clickListener: ((v: ImageView, movie: Movie?) -> Unit)? = null,
        posterHeight: Int? = null, posterWidth: Int? = null
    ) {
        this.movie = movie

        if (posterHeight != null && posterWidth != null) {
            back_movie_image.layoutParams.height = posterHeight.dp
            back_movie_image.layoutParams.width = posterWidth.dp
            back_details.layoutParams.height = posterHeight.dp
            back_details.layoutParams.width = posterWidth.dp

            front_movie_image.layoutParams.height = posterHeight.dp
            front_movie_image.layoutParams.width = posterWidth.dp
        }

        front_movie_image.loadMoviePoster(movie?.posterPath)
        back_movie_image.loadMoviePoster(movie?.posterPath)

        back_movie_name.text = movie?.title

        back_movie_rating.setVote(movie?.voteAverage)

        flip_view.setOnClickListener {
            it as EasyFlipView
            if (it.currentFlipState == EasyFlipView.FlipState.FRONT_SIDE) {
                it.setFlipTypeFromRight()
            } else {
                it.setFlipTypeFromLeft()
            }
            it.flipTheView()
        }

        back_movie_more_info_button.setOnClickListener {
            back_movie_image.transitionName = movie?.id
            clickListener?.invoke(back_movie_image, movie)
        }
    }

}