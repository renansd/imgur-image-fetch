package com.example.imgurimagefetch.di

import com.example.network.RetrofitInitialization
import com.example.network.imagefetch.ImageFetchContract
import com.example.network.imagefetch.ImageFetchRepository
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitInitialization.retrofitAdapterProvider() }
    single { ImageFetchContract() }
    single { ImageFetchRepository(get()) }
}