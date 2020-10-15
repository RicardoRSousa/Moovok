package com.ricardojrsousa.movook.wrappers

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.utils.RoundedTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.lang.Exception
import kotlin.annotation.Target as Target1


/**
 * Created by ricardosousa on 23/03/2020
 */
object PicassoWrapper {

    private const val posterPathPrefix = "https://image.tmdb.org/t/p/w500"
    private const val backdropPathPrefix = "https://image.tmdb.org/t/p/w1280"
    private const val profilePathPrefix = "https://image.tmdb.org/t/p/h632"

    fun loadMoviePoster(url: String?, view: ImageView, callback: Callback?) {
        Picasso.get()
            .load(posterPathPrefix + url)
            .placeholder(R.drawable.poster_placeholder)
            .transform(RoundedTransformation(20f))
            .into(view, callback)
    }

    fun loadMovieBackdrop(url: String?, view: ImageView, callback: Callback? = null) {
        Picasso.get()
            .load(backdropPathPrefix + url)
            .fit()
            .centerCrop()
            .into(view, callback)
    }

    fun loadImageAsViewGroupBackground(url: String?, viewGroup: ViewGroup, callback: Callback? = null) {
        Picasso.get()
            .load(url)
            .into(object : Target {
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    Log.e("Picasso", e?.message)
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: LoadedFrom?) {
                    viewGroup.background = BitmapDrawable(viewGroup.resources, bitmap)
                }

            })
    }

    fun load(url: String?, view: ImageView, callback: Callback? = null){
        Picasso.get()
            .load(url)
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
    PicassoWrapper.loadMoviePoster(url, this, callback)
}

fun ImageView.loadMovieBackdrop(url: String?, callback: Callback? = null) {
    PicassoWrapper.loadMovieBackdrop(url, this, callback)
}

fun ImageView.loadBookCover(url: String?, callback: Callback? = null) {
    PicassoWrapper.loadBookCover(url, this, callback)
}

fun ImageView.loadCastProfileThumbnail(url: String?, callback: Callback? = null) {
    PicassoWrapper.loadCastProfileThumbnail(url, this, callback)
}

fun ImageView.loadImage(url: String?, callback: Callback? = null) {
    PicassoWrapper.load(url, this, callback)
}

fun ConstraintLayout.loadBackdrop(url: String?, callback: Callback? = null) {
    PicassoWrapper.loadImageAsViewGroupBackground(url, this, callback)
}