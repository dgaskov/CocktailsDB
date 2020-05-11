package ru.nsu.alcoHelper.presentation.cocktailDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.nsu.alcoHelper.Application
import ru.nsu.alcoHelper.common.humanReadable
import ru.nsu.alcoHelper.common.setupSchedulers
import ru.nsu.alcoHelper.data.model.Ingredient

class CocktailDetailsViewModel: ViewModel() {
    private val cocktailName = MutableLiveData<String>()
    val getCocktailName: LiveData<String> get() = cocktailName

    private val cocktailIngredients = MutableLiveData<List<Ingredient>>()
    val getCocktailIngredients: LiveData<List<Ingredient>> get() = cocktailIngredients

    private val cocktailReceiptBody = MutableLiveData<String>()
    val getCocktailReceiptBody: LiveData<String> get() = cocktailReceiptBody

    private val cocktailImageURL = MutableLiveData<String>()
    val getCocktailImageURL: LiveData<String> get() = cocktailImageURL

    private val isLoading = MutableLiveData<Boolean>()
    val getIsLoading: LiveData<Boolean> = isLoading

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun start(cocktailId: String): Disposable {
        isLoading.value = true
        return Application.apiProvider.cocktailDBAPI.getCocktailById(cocktailId)
            .setupSchedulers()
            .map {
                val drink = it.drinks.firstOrNull()
                if (drink == null) {
                    throw IllegalStateException("Expected 1 drink, but get zero")
                } else {
                    return@map drink
                }
            }
            .subscribe({
                isLoading.value = false
                cocktailName.value = it?.name
                cocktailIngredients.value = it?.ingredients
                cocktailReceiptBody.value = it?.instructions
                cocktailImageURL.value = it?.image.toString()
            }, {
                isLoading.value = false
                errors.value = it.humanReadable
            })
    }
}