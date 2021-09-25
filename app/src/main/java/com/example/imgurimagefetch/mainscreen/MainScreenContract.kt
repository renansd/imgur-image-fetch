package com.example.imgurimagefetch.mainscreen

interface MainScreenContract {
    interface View {
        fun showLoading()

        fun hideLoading()

        fun onSuccess()

        fun onError()
    }

    interface ViewModel {
        fun getCatImages()
    }
}