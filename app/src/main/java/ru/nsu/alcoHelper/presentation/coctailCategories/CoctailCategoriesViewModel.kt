package ru.nsu.alcoHelper.presentation.coctailCategories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.nsu.alcoHelper.Application
import ru.nsu.alcoHelper.common.humanReadable
import ru.nsu.alcoHelper.common.setupSchedulers
import ru.nsu.alcoHelper.data.model.DrinkCompactInfo

class CoctailCategoriesViewModel: ViewModel() {
    private val coctailCategories = MutableLiveData<List<DrinkCompactInfo>>()
    val getCoctailCategories: LiveData<List<DrinkCompactInfo>> get() = coctailCategories

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun start(categoryName: String): Disposable {
        return Application.apiProvider.coctailDBAPI.getCoctailsByCategory(categoryName)
            .setupSchedulers()
            .subscribe({
                coctailCategories.value = it?.drinks
            }, {
                errors.value = it.humanReadable
            })
    }
}