package ru.nsu.loremPicsum.data.model

import com.google.gson.annotations.SerializedName
import java.net.URL

data class DrinkDetailedInfo(
    @SerializedName("strId")
    val id: String,

    @SerializedName("strDrink")
    val name: String,

    @SerializedName("strCategory")
    val category: String,

    @SerializedName("strInstructions")
    val instructions: String,

    @SerializedName("strDrinkThumb")
    val image: URL,

    @SerializedName("strAlcoholic")
    val alcoholic: String,

    // MARK: - Add those properties to *ingredientNames*

    private val strIngredient1: String?,
    private val strIngredient2: String?,
    private val strIngredient3: String?,
    private val strIngredient4: String?,
    private val strIngredient5: String?,
    private val strIngredient6: String?,
    private val strIngredient7: String?,
    private val strIngredient8: String?,
    private val strIngredient9: String?,
    private val strIngredient10: String?,
    private val strIngredient11: String?,
    private val strIngredient12: String?,
    private val strIngredient13: String?,
    private val strIngredient14: String?,
    private val strIngredient15: String?,


    private val strMeasure1: String?,
    private val strMeasure2: String?,
    private val strMeasure3: String?,
    private val strMeasure4: String?,
    private val strMeasure5: String?,
    private val strMeasure6: String?,
    private val strMeasure7: String?,
    private val strMeasure8: String?,
    private val strMeasure9: String?,
    private val strMeasure10: String?,
    private val strMeasure11: String?,
    private val strMeasure12: String?,
    private val strMeasure13: String?,
    private val strMeasure14: String?,
    private val strMeasure15: String?

    // MARK: - Add those properties to *measureList*
) {
    val ingredientNames: List<String> get() = listOfNotNull(
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15
    )

    val measures: List<String> get() = listOfNotNull(
        strMeasure1,
        strMeasure2,
        strMeasure3,
        strMeasure4,
        strMeasure5,
        strMeasure6,
        strMeasure7,
        strMeasure8,
        strMeasure9,
        strMeasure10,
        strMeasure11,
        strMeasure12,
        strMeasure13,
        strMeasure14,
        strMeasure15
    )

    val ingredients: List<Ingredient> get() {
        return ingredientNames.mapIndexed { index, name ->
            Ingredient(name, measures.elementAtOrElse(index) { "pinch" })
        }
    }
}