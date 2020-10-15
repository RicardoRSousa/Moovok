package com.ricardojrsousa.movook.presentation.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.utils.dp
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import kotlinx.android.synthetic.main.list_item_movie_poster.view.*

class MoviePosterView() : BindableViewHolder<Movie> {

    override lateinit var itemView: View

    var posterHeight: Int? = null

    private val defaultMargin = 4.dp

    private val viewInitialized = MutableLiveData<Boolean>()

    constructor(itemView: View) : this() {
        this.itemView = itemView
    }

    override fun bind(movie: Movie?, position: Int?, clickListener: ((view: ImageView?, t: Movie?) -> Unit)?) {
        with(itemView.movie_poster) {
            loadMoviePoster(movie?.posterPath)
        }
        itemView.setOnClickListener {
            itemView.movie_poster.transitionName = movie?.id
            clickListener?.invoke(itemView.movie_poster, movie)
        }
    }


    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Movie> {
        itemView = layoutInflater.inflate(R.layout.list_item_movie_poster, container, false)
        val h = posterHeight
        if (h != null) {
            itemView.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, h.dp).also { it.setMargins(defaultMargin, defaultMargin, defaultMargin, defaultMargin) }
        }
        viewInitialized.postValue(true)
        return MoviePosterView(itemView)
    }

}