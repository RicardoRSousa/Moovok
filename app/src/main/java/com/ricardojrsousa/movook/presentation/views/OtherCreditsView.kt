package com.ricardojrsousa.movook.presentation.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.presentation.adapters.BindableViewListAdapter
import kotlinx.android.synthetic.main.view_other_credits.view.*

class OtherCreditsView : BindableViewHolder<List<Movie>> {
    override lateinit var itemView: View

    override fun bind(movies: List<Movie>?, clickListener: ((view: ImageView?, t: List<Movie>?) -> Unit)?) {
        if (movies != null) {
            val movieListAdapter = BindableViewListAdapter(MovieListItemView()) { view, movie ->
                clickListener?.invoke(view, listOf(movie!!))
            }.also { it.addItems(movies) }

            with(itemView.other_credits_list) {
                adapter = movieListAdapter
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<List<Movie>> {
        itemView = layoutInflater.inflate(R.layout.view_other_credits, container, false)
        return this
    }

}