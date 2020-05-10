package ru.nsu.loremPicsum.presentation.imageList.list

import ru.nsu.loremPicsum.data.model.ImageMetainfo

interface OnImageClickListener {
    fun onImageClicked(model: ImageMetainfo)
}