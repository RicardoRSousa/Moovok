package com.ricardojrsousa.movook.presentation.adapters.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BindableViewHolder
import kotlinx.android.synthetic.main.view_person_biography.view.*

class BiographyView(layoutInflater: LayoutInflater, container: ViewGroup?) : BindableViewHolder<Person> {
    override val itemView: View = layoutInflater.inflate(R.layout.view_person_biography, container, false)

    override fun bind(person: Person?, clickListener: ((view: ImageView, t: Person?) -> Unit)?) {
        itemView.biography_text_view.text = person?.biography
    }
}