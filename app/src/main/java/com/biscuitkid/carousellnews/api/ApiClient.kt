package com.biscuitkid.carousellnews.api

import android.content.Context
import android.net.ConnectivityManager
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ApiClient{
    fun okHttpClient(context: Context): OkHttpClient {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(10 * 60, TimeUnit.SECONDS).writeTimeout(10 * 60, TimeUnit.SECONDS)
            .readTimeout(10 * 60, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(context)).build()

        return okHttpClient
    }
    fun getRetrofit(context: Context): Retrofit {
        if (retrofit != null ) {
            return retrofit as Retrofit
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://storage.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient(context))
            .build()

        return retrofit as Retrofit
    }

    companion object {
        private val instance by lazy (LazyThreadSafetyMode.NONE){ ApiClient() }
        var retrofit: Retrofit? = null

        fun create(context: Context): ApiEndPoint {
            return instance.getRetrofit(context).create(ApiEndPoint::class.java)
        }

    }


    inner class NoConnectivityException : IOException() {
        // You can send any message whatever you want from here.
        override val message: String
            get() = "No Internet Connection"
    }

    inner class NetworkConnectionInterceptor(private val mContext: Context) : Interceptor {

        private val isConnected: Boolean
            get() {
                val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = connectivityManager.activeNetworkInfo
                return netInfo != null && netInfo.isConnected
            }

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!isConnected) {
                throw NoConnectivityException()
                // Throwing our custom exception 'NoConnectivityException'
            }

            val builder = chain.request().newBuilder()
            builder.addHeader("accept", "application/json")
            return chain.proceed(builder.build())
        }

    }
}