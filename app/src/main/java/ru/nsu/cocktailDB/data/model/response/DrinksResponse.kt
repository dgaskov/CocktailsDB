package ru.nsu.cocktailDB.data.model.response

data class DrinksResponse<DrinkType>(
    val drinks: List<DrinkType>
)