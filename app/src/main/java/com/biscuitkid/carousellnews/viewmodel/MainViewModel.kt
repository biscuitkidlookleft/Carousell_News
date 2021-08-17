package com.biscuitkid.carousellnews.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biscuitkid.carousellnews.api.ApiClient
import com.biscuitkid.carousellnews.api.ResponseCarousellNews
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val context: Context): ViewModel() {

    val resultCarousellNews = MutableLiveData<List<ResponseCarousellNews>>()

    fun getCarousellNews() {
        ApiClient.create(context).getCarousellNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<ResponseCarousellNews>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: List<ResponseCarousellNews>) {
                    resultCarousellNews.value = t
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(context, "Fail to get Data", Toast.LENGTH_LONG).show()

                }

            })

    }
}