package com.ricardojrsousa.movook.presentation.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Genre
import com.ricardojrsousa.movook.utils.getStringResourceByName
import it.beppi.tristatetogglebutton_library.TriStateToggleButton
import it.beppi.tristatetogglebutton_library.TriStateToggleButton.ToggleStatus
import kotlinx.android.synthetic.main.list_item_genre.view.*

/**
 * Created by Ricardo Sousa on 07/12/2020
 */
class GenreViewHolder : BindableViewHolder<Genre> {

    override lateinit var itemView: View

    override fun init(layoutInflater: LayoutInflater, container: ViewGroup?): BindableViewHolder<Genre> {
        itemView = layoutInflater.inflate(R.layout.list_item_genre, container, false)
        return this
    }

    override fun bind(t: Genre?, position: Int?, clickListener: ((view: ImageView?, t: Genre?) -> Unit)?) {
        with(itemView) {
            genre_name.text = t?.name
            genre_switch.tag = t?.name

            genre_switch.setOnToggleChanged { toggleStatus, booleanToggleStatus, toggleIntValue ->
                genre_message.text = when (toggleStatus) {
                    ToggleStatus.off -> context.getStringResourceByName("${t?.name?.toLowerCase()}_off")
                    ToggleStatus.on -> context.getStringResourceByName("${t?.name?.toLowerCase()}_on")
                    ToggleStatus.mid -> ""
                }
            }
        }
    }
}