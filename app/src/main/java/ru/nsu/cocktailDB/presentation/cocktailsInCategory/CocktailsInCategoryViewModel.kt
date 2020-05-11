package ru.nsu.cocktailDB.presentation.cocktailsInCategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.nsu.cocktailDB.Application
import ru.nsu.cocktailDB.common.humanReadable
import ru.nsu.cocktailDB.common.setupSchedulers
import ru.nsu.cocktailDB.data.model.DrinkCompactInfo

class CocktailsInCategoryViewModel: ViewModel() {
    private val cocktailsInCategory = MutableLiveData<List<DrinkCompactInfo>>()
    val getCocktailsInCategory: LiveData<List<DrinkCompactInfo>> get() = cocktailsInCategory

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun start(categoryName: String): Disposable {
        return Application.apiProvider.cocktailDBAPI.getCocktailsByCategory(categoryName)
            .setupSchedulers()
            .subscribe({
                cocktailsInCategory.value = it?.drinks
            }, {
                errors.value = it.humanReadable
            })
    }
}