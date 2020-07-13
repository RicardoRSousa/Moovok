package com.ricardojrsousa.movook.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import kotlinx.android.synthetic.main.movie_list_item.view.*

/**
 * Created by ricardosousa on 23/03/2020
 */
class MovieListAdapter(
    private val onMovieClickListener: ((view: ImageView, movie: Movie) -> Unit)? = null
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var items: ArrayList<Movie> = arrayListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upcomingMovie = items[position] ?: return
        holder.setupView(upcomingMovie)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun addItems(movies: List<Movie>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun setupView(movie: Movie) {
            with(view.movie_poster) {
                loadMoviePoster(movie.posterPath)
                transitionName = movie.posterPath
            }
            view.setOnClickListener { onMovieClickListener?.let { it(view.movie_poster, movie) } }
        }
    }
}