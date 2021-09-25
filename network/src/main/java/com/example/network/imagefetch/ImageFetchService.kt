package com.example.network.imagefetch

import com.example.network.imagefetch.model.ImageFetchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageFetchService {

    @GET("gallery/search/?q=cats")
    fun getCatImages(@Query("page") page: Int): Observable<ImageFetchResponse>
}