package com.anubhav.swiggy.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchResponse {

    @SerializedName("Search")
    @Expose
    private var search: MutableList<Search>? = null

    @SerializedName("totalResults")
    @Expose
    private var totalResults: String? = null

    @SerializedName("Response")
    @Expose
    private var response: String? = null

    @SerializedName("Error")
    @Expose
    private var error: String? = null

    fun getSearch(): MutableList<Search>? {
        return search
    }

    fun setSearch(search: MutableList<Search>?) {
        this.search = search
    }

    fun getTotalResults(): String? {
        return totalResults
    }

    fun setTotalResults(totalResults: String?) {
        this.totalResults = totalResults
    }

    fun getResponse(): String? {
        return response
    }

    fun setResponse(response: String?) {
        this.response = response
    }

    fun getError(): String? {
        return error
    }

    fun setError(error: String?) {
        this.error = error
    }

}