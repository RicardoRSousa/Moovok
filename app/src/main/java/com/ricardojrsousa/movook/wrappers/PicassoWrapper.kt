package com.ricardojrsousa.movook.wrappers

import android.widget.ImageView
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.utils.RoundedTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
 * Created by ricardosousa on 23/03/2020
 */
object PicassoWrapper {

    private val posterPathPrefix = "https://image.tmdb.org/t/p/w500"
    private val backdropPathPrefix = "https://image.tmdb.org/t/p/w1280"
    private val profilePathPrefix = "https://image.tmdb.org/t/p/h632"

    fun loadMoviePoster(url: String, view: ImageView, callback: Callback?) {
        Picasso.get()
            .load(posterPathPrefix + url)
            .placeholder(R.drawable.poster_placeholder)
            .transform(RoundedTransformation(20f))
            .into(view, callback)
    }

    fun loadMovieBackdrop(url: String, view: ImageView, callback: Callback? = null) {
        Picasso.get()
            .load(backdropPathPrefix + url)
            .fit()
            .centerCrop()
            .into(view, callback)
    }


    fun loadCastProfileThumbnail(url: String?, view: ImageView, callback: Callback? = null) {
        Picasso.get()
            .load(profilePathPrefix + url)
            .placeholder(R.drawable.person_placeholder)
            .fit()
            .transform(RoundedTransformation(10f))
            .centerCrop()
            .into(view, callback)
    }

    fun loadBookCover(url: String?, view: ImageView, callback: Callback? = null) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.book_placeholder)
            .transform(RoundedTransformation(5f))
            .into(view, callback)
    }
}

fun ImageView.loadMoviePoster(url: String?, callback: Callback? = null) {
    if (!url.isNullOrBlank())
        PicassoWrapper.loadMoviePoster(url, this, callback)
}

fun ImageView.loadMovieBackdrop(url: String?, callback: Callback? = null) {
    if (!url.isNullOrBlank())
        PicassoWrapper.loadMovieBackdrop(url, this, callback)
}

fun ImageView.loadBookCover(url: String?, callback: Callback? = null) {
    PicassoWrapper.loadBookCover(url, this, callback)
}

fun ImageView.loadCastProfileThumbnail(url: String?, callback: Callback? = null) {
    PicassoWrapper.loadCastProfileThumbnail(url, this, callback)
}