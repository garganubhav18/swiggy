package com.anubhav.swiggy.network.repository

import android.util.Log
import com.anubhav.swiggy.model.SearchResponse
import com.anubhav.swiggy.network.ApiClient
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepo {

    private val searchApi = ApiClient.apiClient!!.getSearchApi()

    fun searchImages(text: String, page:String): Single<SearchResponse?> {
        val url = "https://www.omdbapi.com/?apikey=50ecc816"
        return searchApi.getRepositories(url, text, page)
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
            .onErrorReturn { t -> null}

    }
}