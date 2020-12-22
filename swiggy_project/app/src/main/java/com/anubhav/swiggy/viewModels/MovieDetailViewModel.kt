package com.anubhav.swiggy.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anubhav.swiggy.model.MovieDetail
import com.anubhav.swiggy.network.repository.MovieDetailRepo
import io.reactivex.disposables.CompositeDisposable

class MovieDetailViewModel: ViewModel() {

    private val movieDetailRepo = MovieDetailRepo()
    private val compositeDisposable = CompositeDisposable()
    val movieDetailObservable =  MutableLiveData<MovieDetail?>()

    fun getMovieDetail(imdbId: String) {
        compositeDisposable.add(movieDetailRepo.getMovieDetail(imdbId).subscribe({
            movieDetailObservable.value = it
        }, {
            movieDetailObservable.value = null
        }))

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}