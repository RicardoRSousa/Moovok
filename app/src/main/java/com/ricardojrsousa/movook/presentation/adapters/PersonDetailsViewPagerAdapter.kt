package com.ricardojrsousa.movook.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import com.ricardojrsousa.movook.presentation.adapters.views.BiographyView

private const val TYPE_BIO = 0
private const val TYPE_KNOWN_FOR = 1
private const val TYPE_OTHER_CREDITS = 2

class PersonDetailsViewPagerAdapter : RecyclerView.Adapter<PersonDetailsViewPagerAdapter.ViewHolder>() {

    private var person: Person? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_BIO -> ViewHolder(BiographyView(layoutInflater, parent))
            TYPE_KNOWN_FOR -> ViewHolder(BiographyView(layoutInflater, parent))
            else -> ViewHolder(BiographyView(layoutInflater, parent))
        }
    }

    fun setPerson(person: Person) {
        this.person = person
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(person)
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TYPE_BIO
        1 -> TYPE_KNOWN_FOR
        else -> TYPE_OTHER_CREDITS
    }

    class ViewHolder internal constructor(private val view: BindableViewHolder<Person>) : RecyclerView.ViewHolder(view.itemView) {
        internal fun bind(person: Person?) {
            view.bind(person)
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}