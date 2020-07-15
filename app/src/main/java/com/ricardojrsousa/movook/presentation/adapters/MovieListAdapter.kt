package com.ricardojrsousa.movook.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.presentation.adapters.views.MovieView

/**
 * Created by ricardosousa on 23/03/2020
 */
class MovieListAdapter(val onMovieClickListener: ((view: ImageView, movie: Movie?) -> Unit)? = null) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var items: ArrayList<Movie> = arrayListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MovieView(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    fun addItems(movies: List<Movie>) {
        items.addAll(movies)
        notifyDataSetChanged()
    }

    inner class ViewHolder internal constructor(private val view: BindableViewHolder<Movie>) : RecyclerView.ViewHolder(view.itemView) {
        internal fun bind(movie: Movie?) {
            view.bind(movie, onMovieClickListener)
        }
    }
}