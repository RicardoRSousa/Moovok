package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.flexbox.FlexboxLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Genre
import kotlinx.android.synthetic.main.list_item_genre.view.*

/**
 * Created by Ricardo Sousa on 07/12/2020
 */
class GenreViewHolder(
    private val includedGenres: List<String>,
    private val callback: (genre: Genre, status: Boolean) -> Unit
) : BindableViewHolder<Genre> {

    override lateinit var itemView: View

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Genre> {
        itemView = layoutInflater.inflate(R.layout.list_item_genre, container, false)
        return this
    }

    override fun bind(genre: Genre?, clickListener: ((view: ImageView?, t: Genre?) -> Unit)?) {
        with(itemView) {

            checkable_chip_view.apply {
                text = genre?.name!!
                isChecked = includedGenres.contains(genre.id)
                onCheckedChangeListener = { view, isChecked ->
                    callback.invoke(genre, isChecked)
                }
            }

            val lp: ViewGroup.LayoutParams = checkable_chip_view.layoutParams
            if (lp is FlexboxLayoutManager.LayoutParams) {
                lp.flexGrow = 1.0f
            }
        }
    }
}