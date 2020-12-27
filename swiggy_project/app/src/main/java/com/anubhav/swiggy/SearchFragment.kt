package com.anubhav.swiggy

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anubhav.swiggy.model.SearchResponse
import com.anubhav.swiggy.viewModels.SearchViewModel

class SearchFragment: Fragment(), LoadMoreListener, MovieFragmentListener {

    private var searchViewModel: SearchViewModel? = null
    private var searchEt: EditText? = null
    private var recyclerView: RecyclerView? = null
    private var errorTv: TextView? = null
    private var loaderTv: TextView? = null
    private var activityListener: FragmentActivityListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is FragmentActivityListener) {
            activityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservable()
        errorTv = view.findViewById(R.id.error_tv)
        loaderTv = view.findViewById(R.id.loading_tv)
        searchEt = view.findViewById(R.id.search_et)

        searchEt?.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            var keyCode = 0
            if (event != null) keyCode = event.keyCode
            if (actionId == EditorInfo.IME_ACTION_SEARCH || keyCode == KeyEvent.KEYCODE_ENTER) {
                searchEt?.clearFocus()
                hideSoftInput()
                startSearch(searchEt?.text?.toString()?:"")
                return@OnEditorActionListener true
            }
            false
        })

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun startSearch(text: String) {
        recyclerView?.visibility = View.GONE
        errorTv?.visibility = View.GONE
        loaderTv?.visibility = View.VISIBLE
        searchViewModel!!.getSearchList(text, 1)
    }

    private fun initObservable() {
        searchViewModel?.searchObservable?.observe(this, Observer { searchResponse: SearchResponse? ->
            loaderTv?.visibility = View.GONE
            if(searchResponse != null && !searchResponse.getSearch().isNullOrEmpty()) {
                recyclerView?.visibility = View.VISIBLE
                errorTv?.visibility = View.GONE
                if(recyclerView?.adapter == null) {
                    recyclerView?.adapter = SearchAdapter(ArrayList(searchViewModel!!.model), this, this)
                } else {
                    if(recyclerView?.adapter is SearchAdapter) {
                        (recyclerView?.adapter as SearchAdapter).setPhotos(ArrayList(searchViewModel!!.model))
                    }
                }
            } else {
                recyclerView?.visibility = View.GONE
                errorTv?.visibility = View.VISIBLE
                errorTv?.text = if(searchViewModel!!.errorText.isNotEmpty()) searchViewModel!!.errorText
                                else activity?.getString(R.string.no_movie_found)
            }
        })
    }

    override fun loadMore() {
        if(!searchViewModel!!.isLoading) {
            searchViewModel!!.getSearchList(searchViewModel!!.searchText, searchViewModel!!.currentPage + 1)
        }
    }

    override fun shouldAddLoadMore(): Boolean {
        return searchViewModel!!.currentPage < searchViewModel!!.totalPage
    }

    private fun hideSoftInput() {
        if (activity == null || requireActivity().isFinishing) {
            return
        }
        try {
            val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireActivity().window.decorView.windowToken, 0)
        } catch (e: Exception) {

        }
    }

    override fun onMovieClick(imdbId: String?) {
        if(imdbId != null) {
            activityListener?.launchMovieDetailFragment(imdbId)
        }
    }
}