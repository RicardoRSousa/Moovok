package com.ricardojrsousa.movook.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.model.UpcomingMovie
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import kotlinx.android.synthetic.main.upcoming_movie_list_item.view.*

/**
 * Created by ricardosousa on 23/03/2020
 */
class UpcomingMoviesListAdapter() : RecyclerView.Adapter<UpcomingMoviesListAdapter.ViewHolder>() {

    private var items: List<UpcomingMovie> = listOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.upcoming_movie_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upcomingMovie = items[position] ?: return
        holder.setupView(upcomingMovie)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun getItemId(position: Int): Long {
        return items[position].id.toLong() ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setupView(upcomingMovie: UpcomingMovie) {
            view.upcoming_movie_poster.loadMoviePoster(upcomingMovie.posterPath)
        }
    }
}