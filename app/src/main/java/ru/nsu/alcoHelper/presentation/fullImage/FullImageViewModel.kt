package ru.nsu.alcoHelper.presentation.fullImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.alcoHelper.Application
import ru.nsu.alcoHelper.common.humanReadable
import ru.nsu.alcoHelper.common.setupSchedulers
import ru.nsu.alcoHelper.data.model.ImageDetails

class FullImageViewModel: ViewModel() {
    private val imageDetails = MutableLiveData<ImageDetails>()
    val getImage: LiveData<ImageDetails> get() = imageDetails

    private val errors = MutableLiveData<String>()
    val getErrors: LiveData<String> = errors

    fun fetchImageURL(id: Int) {
        Application.apiProvider.loremPicsumAPI.getImageDetails(id)
                .setupSchedulers()
                .subscribe({
                    imageDetails.value = it
                }, {
                    errors.value = it.humanReadable
                })
    }
}