package com.ricardojrsousa.movook.wrappers

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.framework.api.BACKDROP_PATH_PREFIX
import com.ricardojrsousa.movook.framework.api.POSTER_PATH_PREFIX
import com.ricardojrsousa.movook.framework.api.PROFILE_PATH_PREFIX
import com.ricardojrsousa.movook.utils.RoundedTransformation
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.squareup.picasso.Target
import java.lang.Exception

/**
 * Created by ricardosousa on 23/03/2020
 */
object PicassoWrapper {

    fun loadMoviePoster(url: String?, view: ImageView, callback: Callback?, cornerRadius: Float? = 20f) {
        Picasso.get()
            .load(POSTER_PATH_PREFIX + url)
            .placeholder(R.drawable.poster_placeholder)
            .transform(RoundedTransformation(cornerRadius))
            .into(view, callback)
    }

    fun loadMovieBackdrop(url: String?, view: ImageView, callback: Callback? = null) {
        Picasso.get()
            .load(BACKDROP_PATH_PREFIX + url)
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
            .load(PROFILE_PATH_PREFIX + url)
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

fun ImageView.loadMoviePoster(url: String?, callback: Callback? = null, cornerRadius: Float? = 20f) {
    PicassoWrapper.loadMoviePoster(url, this, callback, cornerRadius)
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