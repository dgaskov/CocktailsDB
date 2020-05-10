package ru.nsu.loremPicsum.data.model

import com.google.gson.annotations.SerializedName
import java.net.URL

data class ImageDetails(
        val id: String,
        val author: String,
        val width: Int,
        val height: Int,
        val url: URL,
        @SerializedName("download_url")
        val downloadURL: URL
)