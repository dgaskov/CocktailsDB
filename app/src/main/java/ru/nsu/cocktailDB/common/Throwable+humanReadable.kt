package ru.nsu.cocktailDB.common

val Throwable.humanReadable: String get() {
    return localizedMessage ?: message ?: "Error"
}