package ru.nsu.loremPicsum.common

val Throwable.humanReadable: String get() {
    return localizedMessage ?: message ?: "Error"
}