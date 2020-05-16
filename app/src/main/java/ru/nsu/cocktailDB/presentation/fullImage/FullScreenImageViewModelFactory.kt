@file:Suppress("UNCHECKED_CAST")

package ru.nsu.cocktailDB.presentation.fullImage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FullScreenImageViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FullScreenImageViewModel(context) as T
    }
}