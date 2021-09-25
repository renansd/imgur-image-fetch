package com.example.network.imagefetch

import com.example.network.imagefetch.model.ImageFetchResponse
import io.reactivex.Observable
import retrofit2.Retrofit

class ImageFetchRepository(retrofit: Retrofit) {

    private var imageFetchService: ImageFetchService = retrofit.create(ImageFetchService::class.java)

    fun getCatImages(): Observable<ImageFetchResponse> =
        imageFetchService.getCatImages()
}