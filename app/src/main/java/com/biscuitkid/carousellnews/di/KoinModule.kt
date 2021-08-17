package com.biscuitkid.carousellnews.di

import com.biscuitkid.carousellnews.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { MainViewModel(androidApplication()) }
}