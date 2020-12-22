package com.anubhav.swiggy

import android.app.Application
import android.content.Context

class SwiggyApplication: Application() {

    @Override
    override fun onCreate() {
        super.onCreate()
        setContext(this)
    }

    companion object {

        lateinit var context: SwiggyApplication
            private set

        fun setContext(context: Context) {
            this.context = context as SwiggyApplication
        }
    }
}