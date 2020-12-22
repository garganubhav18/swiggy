package com.anubhav.swiggy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), FragmentActivityListener {

    private var loaderTv: TextView? = null
    private var fragmentContainer: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loaderTv = findViewById(R.id.loading_tv)
        fragmentContainer = findViewById(R.id.fragment_container)
        launchSearchFragment()
    }

    override fun launchMovieDetailFragment(imdbId: String) {
        val fragment = MovieDetailFragment.newInstance(imdbId)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.tag)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }

    override fun startLoader() {
        loaderTv?.visibility = View.VISIBLE
        fragmentContainer?.visibility = View.GONE

    }

    override fun stopLoader() {
        loaderTv?.visibility = View.GONE
        fragmentContainer?.visibility = View.VISIBLE
    }

    override fun launchSearchFragment() {
        val searchFragment = SearchFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, searchFragment, searchFragment.tag)
        fragmentTransaction.commit()
    }
}