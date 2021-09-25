package com.example.imgurimagefetch.mainscreen

import com.example.network.imagefetch.model.ImageFetchResponse

interface MainScreenContract {
    interface View {
        fun showLoading()

        fun hideLoading()

        fun onSuccess(result: ImageFetchResponse)

        fun onError(throwable: Throwable)
    }

    interface ViewModel {
        fun getCatImages(page: Int)
    }
}