package ru.nsu.loremPicsum.data.network

import io.reactivex.Single
import retrofit2.http.GET
import ru.nsu.loremPicsum.data.model.ImageDetails

interface LoremPicsumAPI {
    companion object {
        const val baseUrl = "https://picsum.photos/"
    }

    @GET("/v2/list")
    fun getImageList(): Single<Array<ImageDetails>>
}