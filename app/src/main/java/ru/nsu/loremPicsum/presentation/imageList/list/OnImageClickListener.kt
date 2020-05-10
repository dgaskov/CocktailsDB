package ru.nsu.loremPicsum.presentation.imageList.list

import ru.nsu.loremPicsum.data.model.ImageDetails

interface OnImageClickListener {
    fun onImageClicked(model: ImageDetails)
}