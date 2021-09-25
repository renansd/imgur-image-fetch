package com.example.imgurimagefetch.mainscreen

import com.example.network.NetworkInteractor
import com.example.network.imagefetch.ImageFetchContract
import com.example.network.imagefetch.model.ImageFetchResponse
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainScreenViewModel(private var view: MainScreenContract.View?) : NetworkInteractor<ImageFetchResponse>(), MainScreenContract.ViewModel, KoinComponent {
    private val imageFetchContract: ImageFetchContract by inject()
    private val page: Int = 0

    override fun getCatImages(page: Int) {
        makeRequest()
        view?.showLoading()
    }

    override fun getRequestObservable(): Observable<ImageFetchResponse> =
        imageFetchContract.getCatImages(page)

    override fun onResult(result: ImageFetchResponse) {
        view?.onSuccess(result)
        view?.hideLoading()
    }

    override fun onError(throwable: Throwable) {
        view?.onError(throwable)
        view?.hideLoading()
    }
}