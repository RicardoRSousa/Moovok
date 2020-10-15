package com.ricardojrsousa.movook.presentation.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import kotlinx.android.synthetic.main.view_person_biography.view.*

class BiographyView : BindableViewHolder<Person> {
    override lateinit var itemView: View

    override fun bind(person: Person?, position: Int?, clickListener: ((view: ImageView?, t: Person?) -> Unit)?) {
        if (person?.biography.isNullOrBlank()) {
            itemView.biography_text_view.text = itemView.context.getString(R.string.no_biography_available)
        } else {
            itemView.biography_text_view.text = person?.biography
        }
    }

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Person> {
        itemView = layoutInflater.inflate(R.layout.view_person_biography, container, false)
        return this
    }
}