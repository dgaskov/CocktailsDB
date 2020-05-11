package ru.nsu.alcoHelper.presentation.cocktailCategories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.nsu.alcoHelper.Application
import ru.nsu.alcoHelper.common.humanReadable
import ru.nsu.alcoHelper.common.setupSchedulers
import ru.nsu.alcoHelper.data.model.CocktailCategory

class CocktailCategoriesViewModel: ViewModel() {
    private val cocktailCategories = MutableLiveData<List<CocktailCategory>>()
    val getCocktailCategories: LiveData<List<CocktailCategory>> get() = cocktailCategories

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun start(): Disposable {
        return Application.apiProvider.cocktailDBAPI.getCocktailCategories()
            .setupSchedulers()
            .subscribe({
                cocktailCategories.value = it?.drinks
            }, {
                errors.value = it.humanReadable
            })
    }
}