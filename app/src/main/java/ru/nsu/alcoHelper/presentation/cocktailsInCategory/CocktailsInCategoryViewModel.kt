package ru.nsu.alcoHelper.presentation.cocktailsInCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.nsu.alcoHelper.Application
import ru.nsu.alcoHelper.common.humanReadable
import ru.nsu.alcoHelper.common.setupSchedulers
import ru.nsu.alcoHelper.data.model.DrinkCompactInfo

class CocktailsInCategoryViewModel: ViewModel() {
    private val cocktailCategories = MutableLiveData<List<DrinkCompactInfo>>()
    val getCocktailCategories: LiveData<List<DrinkCompactInfo>> get() = cocktailCategories

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun start(categoryName: String): Disposable {
        return Application.apiProvider.cocktailDBAPI.getCocktailsByCategory(categoryName)
            .setupSchedulers()
            .subscribe({
                cocktailCategories.value = it?.drinks
            }, {
                errors.value = it.humanReadable
            })
    }
}