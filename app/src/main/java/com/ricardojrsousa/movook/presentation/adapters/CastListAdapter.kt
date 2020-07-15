package com.ricardojrsousa.movook.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.wrappers.loadCastProfileThumbnail
import kotlinx.android.synthetic.main.cast_list_item.view.*

/**
 * Created by ricardosousa on 23/03/2020
 */
class CastListAdapter(
    private val onCastClickListener: ((view: ImageView, person: Person) -> Unit)? = null
) : RecyclerView.Adapter<CastListAdapter.ViewHolder>() {

    private var items: ArrayList<Person> = arrayListOf()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cast_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cast = items[position]
        holder.bind(cast)
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

    fun addItems(people: List<Person>) {
        items.addAll(people)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        internal fun bind(person: Person) {
            with(view.cast_image) {
                loadCastProfileThumbnail(person.profilePath)
                //transitionName = cast.id.toString()
            }
            view.cast_name.text = person.name
            view.cast_character.text = person.character
            view.setOnClickListener { onCastClickListener?.invoke(view.cast_image, person) }
        }
    }
}