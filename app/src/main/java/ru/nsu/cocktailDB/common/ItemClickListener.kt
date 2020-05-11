package ru.nsu.cocktailDB.common

interface ItemClickListener<ItemType> {
    fun itemClicked(item: ItemType)
}