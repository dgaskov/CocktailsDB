package ru.nsu.alcoHelper.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.nsu.alcoHelper.data.model.DrinkCompactInfo
import ru.nsu.alcoHelper.data.model.DrinkDetailedInfo
import ru.nsu.alcoHelper.data.model.response.DrinksResponse

interface CoctailDBAPI {
    companion object {
        const val baseUrl = "https://thecocktaildb.com/api/json/v1/1/"
    }

    @GET("filter.php")
    fun getCoctailsByCategory(@Query("c") category: String): Single<DrinksResponse<DrinkCompactInfo>>

    @GET("lookup.php")
    fun getCoctailById(@Query("i") id: String): Single<DrinksResponse<DrinkDetailedInfo>>
}