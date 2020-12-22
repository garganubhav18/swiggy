package com.anubhav.swiggy

interface FragmentActivityListener {

    fun launchMovieDetailFragment(imdbId: String)
    fun launchSearchFragment()
    fun startLoader()
    fun stopLoader()
}