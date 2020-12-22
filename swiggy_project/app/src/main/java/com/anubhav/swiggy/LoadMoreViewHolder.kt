package com.anubhav.swiggy

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LoadMoreViewHolder(view: View, private val listener: LoadMoreListener): RecyclerView.ViewHolder(view) {

    fun setLoader() {
        listener.loadMore()
    }
}