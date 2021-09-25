package com.example.imgurimagefetch

import android.app.Application
import com.example.imgurimagefetch.di.koinModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ImgurImageFetchApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(koinModule)
        }
    }
}