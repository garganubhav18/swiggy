package com.anubhav.swiggy

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anubhav.swiggy.model.Search
import com.bumptech.glide.Glide

class ProductViewHolder(private val view: View, private val context: Context,
                        private val movieFragmentListener: MovieFragmentListener) : RecyclerView.ViewHolder(view) {

    private val imageIv = view.findViewById<ImageView>(R.id.productIv)
    private val nameTv = view.findViewById<TextView>(R.id.movie_name_tv)
    private val yearTv = view.findViewById<TextView>(R.id.year_tv)

    fun setImage(photo: Search) {
        nameTv.text = photo.getTitle()
        yearTv.text = photo.getYear()
        val url = photo.getPoster()
        Glide.with(context).load(url).placeholder(R.drawable.ic_launcher_background).into(imageIv)

        view.setOnClickListener { movieFragmentListener.onMovieClick(photo.getImdbID()) }
    }
}