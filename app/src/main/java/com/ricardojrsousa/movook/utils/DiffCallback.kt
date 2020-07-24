package com.ricardojrsousa.movook.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.ricardojrsousa.movook.core.data.Identifiable


class DiffCallback<T : Identifiable>(newList: List<T>, oldList: List<T>) : DiffUtil.Callback() {

    var oldList: List<T> = oldList
    var newList: List<T> = newList

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

}