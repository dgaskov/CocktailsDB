package ru.nsu.cocktailDB.data.model

import com.google.gson.annotations.SerializedName

data class CocktailCategory(
    @SerializedName("strCategory")
    val name: String
)