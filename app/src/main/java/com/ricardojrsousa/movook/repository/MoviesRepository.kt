package com.ricardojrsousa.movook.repository

import com.ricardojrsousa.movook.api.MoviesAPI
import com.ricardojrsousa.movook.model.UpcomingMovie
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ricardosousa on 19/03/2020
 */
class MoviesRepository(private val moviesApi: MoviesAPI) {

    fun getUpcomingMovies(page: Int): Single<List<UpcomingMovie>> {
        return moviesApi.getUpcomingMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.results }
    }
}