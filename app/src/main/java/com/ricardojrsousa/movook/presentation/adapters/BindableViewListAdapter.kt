package com.ricardojrsousa.movook.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ricardojrsousa.movook.core.data.Identifiable
import com.ricardojrsousa.movook.presentation.BindableViewHolder


/**
 * Created by ricardosousa on 17/07/2020
 */
class BindableViewListAdapter<T : Identifiable>(val view: BindableViewHolder<T>, val onClickListener: ((view: ImageView?, item: T?) -> Unit)? = null) :
    ListAdapter<Identifiable, BindableViewListAdapter<T>.ViewHolder>(diffCallback) {

    private var items: ArrayList<T> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(view.init(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun addItems(listItems: List<T>) {
        items.addAll(listItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder internal constructor(private val view: BindableViewHolder<T>) : RecyclerView.ViewHolder(view.itemView) {
        internal fun bind(item: T?, position: Int) {
            view.bind(item, position, onClickListener)
        }
    }

    companion object {
        val diffCallback: DiffUtil.ItemCallback<Identifiable> = object : DiffUtil.ItemCallback<Identifiable>() {
            override fun areItemsTheSame(oldItem: Identifiable, newItem: Identifiable): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Identifiable, newItem: Identifiable): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

}