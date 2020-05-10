package ru.nsu.loremPicsum.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.nsu.loremPicsum.data.model.ImageDetails
import ru.nsu.loremPicsum.data.model.ImageMetainfo

interface LoremPicsumAPI {
    companion object {
        const val baseUrl = "https://picsum.photos/"
    }

    @GET("/v2/list")
    fun getImageIds(): Single<Array<ImageMetainfo>>

    @GET("/id/{id}/info")
    fun getImageDetails(@Path("id") id: Int): Single<ImageDetails>
}