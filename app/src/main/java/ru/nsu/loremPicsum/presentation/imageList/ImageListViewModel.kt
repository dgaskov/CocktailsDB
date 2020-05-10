package ru.nsu.loremPicsum.presentation.imageList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.loremPicsum.Application
import ru.nsu.loremPicsum.common.humanReadable
import ru.nsu.loremPicsum.common.setupSchedulers
import ru.nsu.loremPicsum.data.model.ImageMetainfo

class ImageListViewModel: ViewModel() {
    private val imageDetailsList = MutableLiveData<List<ImageMetainfo>>()
    val getImageList: LiveData<List<ImageMetainfo>> get() = imageDetailsList

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun fetchImages() {
        Application.apiProvider.loremPicsumAPI.getImageIds()
                .setupSchedulers()
                .subscribe({
                    imageDetailsList.value = it.asList()
                }, {
                    errors.value = it.humanReadable
                })
    }
}