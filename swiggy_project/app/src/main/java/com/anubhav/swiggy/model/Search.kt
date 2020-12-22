package com.anubhav.swiggy.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Search {

    @SerializedName("Title")
    @Expose
    private var title: String? = null

    @SerializedName("Year")
    @Expose
    private var year: String? = null

    @SerializedName("imdbID")
    @Expose
    private var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    private var type: String? = null

    @SerializedName("Poster")
    @Expose
    private var poster: String? = null

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getYear(): String? {
        return year
    }

    fun setYear(year: String?) {
        this.year = year
    }

    fun getImdbID(): String? {
        return imdbID
    }

    fun setImdbID(imdbID: String?) {
        this.imdbID = imdbID
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun getPoster(): String? {
        return poster
    }

    fun setPoster(poster: String?) {
        this.poster = poster
    }

}