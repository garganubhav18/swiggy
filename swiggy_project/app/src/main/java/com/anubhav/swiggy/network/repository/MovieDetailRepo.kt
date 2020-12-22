package com.anubhav.swiggy.network.repository

import android.util.Log
import com.anubhav.swiggy.model.MovieDetail
import com.anubhav.swiggy.network.ApiClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailRepo {

    val movieDetailInterface = ApiClient.apiClient!!.getMovieDetailApi()

    fun getMovieDetail(imdbId:String): Single<MovieDetail?> {
        val url = "https://www.omdbapi.com/?apikey=50ecc816"
        return movieDetailInterface.getMovieDetail(url, imdbId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                val searchResponse = response.body()
                if(response.isSuccessful && searchResponse != null) {
                    searchResponse
                } else {
                    null
                }
            }
            .onErrorReturn { t -> null }

    }
}
