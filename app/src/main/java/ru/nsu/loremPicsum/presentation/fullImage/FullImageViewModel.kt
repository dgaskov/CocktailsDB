package ru.nsu.loremPicsum.presentation.fullImage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nsu.loremPicsum.Application
import ru.nsu.loremPicsum.common.humanReadable
import ru.nsu.loremPicsum.common.setupSchedulers
import ru.nsu.loremPicsum.data.model.ImageDetails

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