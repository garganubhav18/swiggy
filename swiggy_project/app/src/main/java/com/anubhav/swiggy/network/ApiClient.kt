package com.anubhav.swiggy.network

import android.content.Context
import android.net.ConnectivityManager
import com.anubhav.swiggy.SwiggyApplication
import com.anubhav.swiggy.network.repository.MovieDetailInterface
import com.anubhav.swiggy.network.repository.SearchApiInterface
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    private var retrofit: Retrofit? = null
    private val cacheSize = (5 * 1024 * 1024).toLong()
    private val cache =
        Cache(SwiggyApplication.context.cacheDir, cacheSize)
    private val okHttpClient =
        OkHttpClient.Builder().cache(cache).addInterceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            request = if (hasNetwork(SwiggyApplication.context)) {
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            } else {
                request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 2)
                    .build()
            }
            chain.proceed(request)
        }.build()

    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }

    private fun hasNetwork(context: Context): Boolean {
        var isConnected = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) isConnected = true
        return isConnected
    }

    companion object {
        var apiClient: ApiClient ? = null
            get() {
                if (field == null) {
                    synchronized(ApiClient::class.java) {
                        if (field == null) {
                            field = ApiClient()
                        }
                    }
                }
                return field
            }
            private set
        private const val BASE_URL = "https://www.omdbapi.com"
    }

    fun getSearchApi(): SearchApiInterface {
        return client!!.create(SearchApiInterface::class.java)
    }

    fun getMovieDetailApi(): MovieDetailInterface {
        return client!!.create(MovieDetailInterface::class.java)
    }
 }