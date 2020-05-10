package ru.nsu.loremPicsum.presentation.imageList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.loremPicsum.Application
import ru.nsu.loremPicsum.common.setupSchedulers
import ru.nsu.loremPicsum.data.model.ImageDetails

class ImageListViewModel: ViewModel() {
    private val imageDetailsList = MutableLiveData<List<ImageDetails>>()
    val getImageList: LiveData<List<ImageDetails>> get() = imageDetailsList

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun fetchImages() {
        Application.apiProvider.loremPicsumAPI.getImageList()
                .setupSchedulers()
                .subscribe({
                    imageDetailsList.value = it.asList()
                }, {
                    errors.value = it.localizedMessage ?: it.message ?: "Error"
                })
    }
}