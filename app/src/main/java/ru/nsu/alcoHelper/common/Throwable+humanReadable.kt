package ru.nsu.alcoHelper.common

val Throwable.humanReadable: String get() {
    return localizedMessage ?: message ?: "Error"
}