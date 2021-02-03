package com.ricardojrsousa.movook.utils

import android.content.Context
import android.content.res.Resources
import com.ricardojrsousa.movook.core.data.Movie

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Float.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun List<Movie>.filterAdult(): List<Movie> = this.filter { !it.adult }

fun List<Movie>.filterByVoteCount(minVoteCount: Int): List<Movie> = this.filter { it.voteCount > minVoteCount }

fun Context.getStringResourceByName(stringName: String): String {
    val resId = resources.getIdentifier(stringName, "string", packageName)
    return getString(resId)
}