package com.anubhav.swiggy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anubhav.swiggy.model.MovieDetail
import com.anubhav.swiggy.viewModels.MovieDetailViewModel
import com.anubhav.swiggy.viewModels.SearchViewModel
import com.bumptech.glide.Glide

class MovieDetailFragment: Fragment() {

    private var movieDetailViewModel: MovieDetailViewModel? = null
    private var productIv : ImageView? = null
    private var movieNameTv: TextView? = null
    private var yearTv: TextView? = null
    private var ratedTv: TextView? = null
    private var releasedTv: TextView? = null
    private var runtimeTv: TextView? = null
    private var genreTv: TextView? = null
    private var directorTv: TextView? = null
    private var activityListener: FragmentActivityListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is FragmentActivityListener) {
            activityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservable()
        productIv = view.findViewById(R.id.productIv)
        movieNameTv = view.findViewById(R.id.movie_name_tv)
        yearTv = view.findViewById(R.id.year_tv)
        ratedTv = view.findViewById(R.id.rated_tv)
        releasedTv = view.findViewById(R.id.released_tv)
        runtimeTv = view.findViewById(R.id.runTime_tv)
        genreTv = view.findViewById(R.id.genre_tv)
        directorTv = view.findViewById(R.id.director_tv)

        val imdbId = arguments?.getString(KEY_IMDB_ID)
        if(imdbId != null) {
            activityListener?.startLoader()
            movieDetailViewModel?.getMovieDetail(imdbId)
        }

    }

    private fun initObservable() {
        movieDetailViewModel?.movieDetailObservable?.observe(this, Observer { movieDetail ->
            activityListener?.stopLoader()
            if(movieDetail != null) {
                setMovieDetail(movieDetail)
            } else {
                Toast.makeText(context!!, getString(R.string.movie_detail_load_error), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setMovieDetail(movieDetail: MovieDetail) {
        Glide.with(context!!).load(movieDetail.getPoster()).placeholder(R.drawable.ic_launcher_background).into(productIv!!)
        movieNameTv?.text = movieDetail.getTitle()
        yearTv?.text = movieDetail.getYear()
        ratedTv?.text = movieDetail.getRated()
        releasedTv?.text = movieDetail.getReleased()
        runtimeTv?.text = movieDetail.getRuntime()
        genreTv?.text = movieDetail.getGenre()
        directorTv?.text = movieDetail.getDirector()
    }

    companion object {
        const val KEY_IMDB_ID = "KEY_IMDB_ID"

        fun newInstance(imdbId: String) : MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putString(KEY_IMDB_ID, imdbId)
            fragment.arguments = bundle
            return fragment
        }
    }
}