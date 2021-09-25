package com.example.network.imagefetch.model

data class ImageFetchResponse(val data: ArrayList<ImageFetchData?>, val success: Boolean, val status: Int) {

    data class ImageFetchData(val title: String, val link: String, val images: ArrayList<Image>?) {

        data class Image(val type: String, val link: String, val mp4: String)
    }
}