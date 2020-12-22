package com.anubhav.swiggy.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anubhav.swiggy.model.Search
import com.anubhav.swiggy.model.SearchResponse
import com.anubhav.swiggy.network.repository.SearchRepo
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel: ViewModel() {
    private val searchRepo = SearchRepo()
    private val compositeDisposable = CompositeDisposable()

    val searchObservable = MutableLiveData<SearchResponse?>()
    var currentPage: Int = 1
    var totalPage:Int = 0
    val model =  ArrayList<Search>()
    var errorText = ""

    var searchText: String = ""

    var isLoading = false

    fun getSearchList(text: String, page: Int) {
        errorText = ""
        currentPage = page
        searchText = text
        isLoading = true
        if(currentPage == 1) {
            model.clear()
            totalPage = 0
        }
        compositeDisposable.add(searchRepo.searchImages(text, page.toString()).subscribe({
            isLoading = false
            if(it != null) {
                updateModel(it)
            }
            searchObservable.value = it
        }, {
            isLoading = false
            searchObservable.value = null
        }))
    }

    private fun updateModel(searchResponse: SearchResponse) {
        if(!searchResponse.getTotalResults().isNullOrEmpty() && !searchResponse.getSearch().isNullOrEmpty()) {
            if(currentPage == 1) {
                model.clear()
            }
            model.addAll(searchResponse.getSearch()!!)
            currentPage++
            totalPage = if(searchResponse.getTotalResults()!!.toInt()%(searchResponse.getSearch()!!.size) == 0) {
                searchResponse.getTotalResults()!!.toInt() / (searchResponse.getSearch()!!.size)
            } else {
                (searchResponse.getTotalResults()!!.toInt() / (searchResponse.getSearch()!!.size)) + 1
            }
        } else {
            currentPage--
            if(!searchResponse.getError().isNullOrEmpty()) {
                errorText = searchResponse.getError()!!
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}