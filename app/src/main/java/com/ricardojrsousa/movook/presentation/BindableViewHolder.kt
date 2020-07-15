package com.ricardojrsousa.movook.presentation

import android.view.View
import android.widget.ImageView

interface BindableViewHolder<T> {
    val itemView: View
    fun bind(t: T?, clickListener: ((view: ImageView, t: T?) -> Unit)? = null)
}