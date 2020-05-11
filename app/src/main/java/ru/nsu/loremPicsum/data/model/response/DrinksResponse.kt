package ru.nsu.loremPicsum.data.model.response

data class DrinksResponse<DrinkType>(
    val drinks: List<DrinkType>
)