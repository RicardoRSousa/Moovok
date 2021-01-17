package com.ricardojrsousa.movook.presentation.persondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.core.data.Movie
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.viewHolders.BindableViewHolder
import com.ricardojrsousa.movook.presentation.viewHolders.BiographyViewHolder
import com.ricardojrsousa.movook.presentation.viewHolders.KnownForViewHolder
import com.ricardojrsousa.movook.presentation.viewHolders.OtherCreditsViewHolder

private const val TYPE_BIO = 0
private const val TYPE_KNOWN_FOR = 1
private const val TYPE_OTHER_CREDITS = 2

class PersonDetailsViewPagerAdapter(val onMovieClickListener: ((view: ImageView?, movie: List<Movie>?) -> Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var person: Person? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_BIO -> PersonViewHolder(BiographyViewHolder().init(layoutInflater, parent))
            TYPE_KNOWN_FOR -> MovieViewHolder(KnownForViewHolder().init(layoutInflater, parent))
            else -> MovieViewHolder(OtherCreditsViewHolder().init(layoutInflater, parent))
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