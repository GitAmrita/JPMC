package com.example.jpmc.network

fun getErrorMessages(responseCode: Int): String {
    return when(responseCode) {
        in 400..499 -> " Bad request, client error."
        in 300..399 -> " Temporarily unavailable, try later."
        in 500..599 -> " Internal server error."
        else -> "Unknown error occurred."
    }
}