package com.anubhav.swiggy.network.repository

import com.anubhav.swiggy.model.MovieDetail
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MovieDetailInterface {

    @GET
    fun getMovieDetail(@Url url: String,
                        @Query("i") imdbId: String): Single<Response<MovieDetail>>
}