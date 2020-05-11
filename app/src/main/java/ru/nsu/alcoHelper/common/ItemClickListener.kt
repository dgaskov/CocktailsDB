package ru.nsu.alcoHelper.common

interface ItemClickListener<ItemType> {
    fun itemClicked(item: ItemType)
}