package com.anubhav.swiggy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anubhav.swiggy.model.Search

class SearchAdapter(private var photos: List<Search>, private val listener: LoadMoreListener,
                    private val movieFragmentListener: MovieFragmentListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == LOAD_MORE_VIEW_TYPE) {
             LoadMoreViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.loader_view, parent, false), listener)
        } else {
            ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.prouct_view, parent, false), parent.context,
                movieFragmentListener)
        }
    }

    override fun getItemCount(): Int {
        return if(shouldAddLoadMore()) {
            photos.size + 1;
        } else {
            photos.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position < photos.size) {
            PRODUCT_VIEW_TYPE
        } else {
            LOAD_MORE_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ProductViewHolder) {
            holder.setImage(photos[position])
        } else if(holder is LoadMoreViewHolder){
            holder.setLoader()
        }
    }


    private fun shouldAddLoadMore() : Boolean {
        return listener.shouldAddLoadMore()
    }

    companion object{
        private const val PRODUCT_VIEW_TYPE = 0
        private const val LOAD_MORE_VIEW_TYPE = 1
    }

    fun setPhotos(photos: List<Search>) {
        this.photos = photos
        notifyDataSetChanged()
    }
}