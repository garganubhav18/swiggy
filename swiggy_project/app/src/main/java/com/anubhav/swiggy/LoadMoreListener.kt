package com.anubhav.swiggy

interface LoadMoreListener {

    fun loadMore()

    fun shouldAddLoadMore() : Boolean
}