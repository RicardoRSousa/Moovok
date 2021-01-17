package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

interface BindableViewHolder<T> {
    var itemView: View
    fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<T>
    fun bind(t: T?, position: Int? = 0, clickListener: ((view: ImageView?, t: T?) -> Unit)? = null)
}