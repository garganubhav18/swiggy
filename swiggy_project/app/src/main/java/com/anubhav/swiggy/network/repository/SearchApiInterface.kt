package com.anubhav.swiggy.network.repository

import com.anubhav.swiggy.model.SearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SearchApiInterface {

    @GET
    fun getRepositories(@Url url: String,
                        @Query("s") text: String?,
                        @Query("page") page:String?): Single<Response<SearchResponse?>>
}