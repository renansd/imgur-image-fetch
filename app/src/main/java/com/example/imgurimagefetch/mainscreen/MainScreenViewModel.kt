package com.example.imgurimagefetch.mainscreen

import android.util.Log
import com.example.network.NetworkInteractor
import com.example.network.imagefetch.ImageFetchContract
import com.example.network.imagefetch.model.ImageFetchResponse
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainScreenViewModel : NetworkInteractor<ImageFetchResponse>(), MainScreenContract.ViewModel, KoinComponent {
    private val imageFetchContract: ImageFetchContract by inject()

    override fun getCatImages() {
        makeRequest()
    }

    override fun getRequestObservable(): Observable<ImageFetchResponse> =
        imageFetchContract.getCatImages()

    override fun onResult(result: ImageFetchResponse) {
        Log.d("Test", "Images fetching successful")
    }

    override fun onError(throwable: Throwable) {
        Log.d("Test", "Images fetching unsuccessful")
    }
}