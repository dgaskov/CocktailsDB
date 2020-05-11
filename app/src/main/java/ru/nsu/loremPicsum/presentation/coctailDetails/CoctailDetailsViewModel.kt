package ru.nsu.loremPicsum.presentation.coctailDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.nsu.loremPicsum.Application
import ru.nsu.loremPicsum.common.humanReadable
import ru.nsu.loremPicsum.common.setupSchedulers
import ru.nsu.loremPicsum.data.model.DrinkDetailedInfo
import ru.nsu.loremPicsum.data.model.Ingredient

class CoctailDetailsViewModel: ViewModel() {
    private val coctailName = MutableLiveData<String>()
    val getCoctailName: LiveData<String> get() = coctailName

    private val coctailIngredients = MutableLiveData<List<Ingredient>>()
    val getCoctailIngredients: LiveData<List<Ingredient>> get() = coctailIngredients

    private val coctailReceiptBody = MutableLiveData<String>()
    val getCoctailReceiptBody: LiveData<String> get() = coctailReceiptBody

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun start(coctailId: Int): Disposable {
        return Application.apiProvider.coctailDBAPI.getCoctailById(11007.toString())
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
                coctailName.value = it?.name
                coctailIngredients.value = it?.ingredients
                coctailReceiptBody.value = it?.instructions
            }, {
                errors.value = it.humanReadable
            })
    }
}