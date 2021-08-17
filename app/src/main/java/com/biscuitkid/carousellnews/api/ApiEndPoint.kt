package com.biscuitkid.carousellnews.api

import io.reactivex.Single
import retrofit2.http.GET

interface ApiEndPoint {

    @GET("carousell-interview-assets/android/carousell_news.json")
    fun getCarousellNews(): Single<List<ResponseCarousellNews>>

}