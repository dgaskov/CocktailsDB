package ru.nsu.cocktailDB.data.model

import com.google.gson.annotations.SerializedName
import java.net.URL

data class DrinkCompactInfo(
    @SerializedName("idDrink")
    val id: String,

    @SerializedName("strDrink")
    val name: String,

    @SerializedName("strDrinkThumb")
    val url: URL
)