package ru.nsu.alcoHelper.data.model.response

data class DrinksResponse<DrinkType>(
    val drinks: List<DrinkType>
)