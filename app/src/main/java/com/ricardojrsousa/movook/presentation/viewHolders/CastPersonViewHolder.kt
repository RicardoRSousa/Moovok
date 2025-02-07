package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.wrappers.loadCastProfileThumbnail
import kotlinx.android.synthetic.main.list_item_cast.view.*

class CastPersonViewHolder : BindableViewHolder<Person> {

    override lateinit var itemView: View

    override fun bind(person: Person?, clickListener: ((view: ImageView?, t: Person?) -> Unit)?) {
        with(itemView.cast_image) {
            loadCastProfileThumbnail(person?.profilePath)
        }
        itemView.cast_name.text = person?.name
        itemView.cast_character.text = person?.character
        itemView.setOnClickListener { clickListener?.invoke(itemView.cast_image, person) }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Person> {
        itemView = layoutInflater.inflate(R.layout.list_item_cast, container, false)
        return this
    }

}