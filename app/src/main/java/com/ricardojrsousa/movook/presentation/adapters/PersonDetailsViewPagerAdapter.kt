package com.ricardojrsousa.movook.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.presentation.views.BiographyView
import com.ricardojrsousa.movook.presentation.views.KnownForView
import com.ricardojrsousa.movook.presentation.views.OtherCreditsView

private const val TYPE_BIO = 0
private const val TYPE_KNOWN_FOR = 1
private const val TYPE_OTHER_CREDITS = 2

class PersonDetailsViewPagerAdapter(val onMovieClickListener: ((view: ImageView?, movie: List<Movie>?) -> Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var person: Person? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_BIO -> PersonViewHolder(BiographyView().init(layoutInflater, parent))
            TYPE_KNOWN_FOR -> MovieViewHolder(KnownForView().init(layoutInflater, parent))
            else -> MovieViewHolder(OtherCreditsView().init(layoutInflater, parent))
        }
    }

    fun setPerson(person: Person) {
        this.person = person
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_BIO -> (holder as PersonViewHolder).bind(person)
            TYPE_KNOWN_FOR -> (holder as MovieViewHolder).bind(person?.credits?.getPopularMovies(9, 100))
            else -> (holder as MovieViewHolder).bind(person?.getCreditsByReleaseDateDescending())
        }
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TYPE_BIO
        1 -> TYPE_KNOWN_FOR
        else -> TYPE_OTHER_CREDITS
    }

    inner class PersonViewHolder(private val view: BindableViewHolder<Person>) : RecyclerView.ViewHolder(view.itemView) {
        internal fun bind(person: Person?) {
            view.bind(person)
        }
    }

    inner class MovieViewHolder(private val view: BindableViewHolder<List<Movie>>) : RecyclerView.ViewHolder(view.itemView) {
        internal fun bind(movies: List<Movie>?) {

            view.bind(movies, clickListener = onMovieClickListener)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}