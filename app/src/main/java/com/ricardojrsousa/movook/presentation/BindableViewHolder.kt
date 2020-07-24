package com.ricardojrsousa.movook.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

interface BindableViewHolder<T> {
    val itemView: View
    fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<T>
    fun bind(t: T?, clickListener: ((view: ImageView?, t: T?) -> Unit)? = null)
}