package com.example.network.imagefetch

import com.example.network.BaseContract
import com.example.network.imagefetch.model.ImageFetchResponse
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ImageFetchContract : BaseContract(), KoinComponent {

    private val imageFetchRepository: ImageFetchRepository by inject()

    fun getCatImages(page: Int): Observable<ImageFetchResponse> =
        imageFetchRepository.getCatImages(page).applySchedulers().share()
}