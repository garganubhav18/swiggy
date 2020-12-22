package com.anubhav.swiggy.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MovieDetail {

    @SerializedName("Title")
    @Expose
    private var title: String? = null

    @SerializedName("Year")
    @Expose
    private var year: String? = null

    @SerializedName("Rated")
    @Expose
    private var rated: String? = null

    @SerializedName("Released")
    @Expose
    private var released: String? = null

    @SerializedName("Runtime")
    @Expose
    private var runtime: String? = null

    @SerializedName("Genre")
    @Expose
    private var genre: String? = null

    @SerializedName("Director")
    @Expose
    private var director: String? = null

    @SerializedName("Writer")
    @Expose
    private var writer: String? = null

    @SerializedName("Actors")
    @Expose
    private var actors: String? = null

    @SerializedName("Plot")
    @Expose
    private var plot: String? = null

    @SerializedName("Language")
    @Expose
    private var language: String? = null

    @SerializedName("Country")
    @Expose
    private var country: String? = null

    @SerializedName("Awards")
    @Expose
    private var awards: String? = null

    @SerializedName("Poster")
    @Expose
    private var poster: String? = null

    @SerializedName("Ratings")
    @Expose
    private var ratings: List<Any?>? = null

    @SerializedName("Metascore")
    @Expose
    private var metascore: String? = null

    @SerializedName("imdbRating")
    @Expose
    private var imdbRating: String? = null

    @SerializedName("imdbVotes")
    @Expose
    private var imdbVotes: String? = null

    @SerializedName("imdbID")
    @Expose
    private var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    private var type: String? = null

    @SerializedName("totalSeasons")
    @Expose
    private var totalSeasons: String? = null

    @SerializedName("Response")
    @Expose
    private var response: String? = null

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

    fun getRated(): String? {
        return rated
    }

    fun setRated(rated: String?) {
        this.rated = rated
    }

    fun getReleased(): String? {
        return released
    }

    fun setReleased(released: String?) {
        this.released = released
    }

    fun getRuntime(): String? {
        return runtime
    }

    fun setRuntime(runtime: String?) {
        this.runtime = runtime
    }

    fun getGenre(): String? {
        return genre
    }

    fun setGenre(genre: String?) {
        this.genre = genre
    }

    fun getDirector(): String? {
        return director
    }

    fun setDirector(director: String?) {
        this.director = director
    }

    fun getWriter(): String? {
        return writer
    }

    fun setWriter(writer: String?) {
        this.writer = writer
    }

    fun getActors(): String? {
        return actors
    }

    fun setActors(actors: String?) {
        this.actors = actors
    }

    fun getPlot(): String? {
        return plot
    }

    fun setPlot(plot: String?) {
        this.plot = plot
    }

    fun getLanguage(): String? {
        return language
    }

    fun setLanguage(language: String?) {
        this.language = language
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String?) {
        this.country = country
    }

    fun getAwards(): String? {
        return awards
    }

    fun setAwards(awards: String?) {
        this.awards = awards
    }

    fun getPoster(): String? {
        return poster
    }

    fun setPoster(poster: String?) {
        this.poster = poster
    }

    fun getRatings(): List<Any?>? {
        return ratings
    }

    fun setRatings(ratings: List<Any?>?) {
        this.ratings = ratings
    }

    fun getMetascore(): String? {
        return metascore
    }

    fun setMetascore(metascore: String?) {
        this.metascore = metascore
    }

    fun getImdbRating(): String? {
        return imdbRating
    }

    fun setImdbRating(imdbRating: String?) {
        this.imdbRating = imdbRating
    }

    fun getImdbVotes(): String? {
        return imdbVotes
    }

    fun setImdbVotes(imdbVotes: String?) {
        this.imdbVotes = imdbVotes
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

    fun getTotalSeasons(): String? {
        return totalSeasons
    }

    fun setTotalSeasons(totalSeasons: String?) {
        this.totalSeasons = totalSeasons
    }

    fun getResponse(): String? {
        return response
    }

    fun setResponse(response: String?) {
        this.response = response
    }
}