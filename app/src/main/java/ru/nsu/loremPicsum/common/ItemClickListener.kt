package ru.nsu.loremPicsum.common

interface ItemClickListener<ItemType> {
    fun itemClicked(item: ItemType)
}