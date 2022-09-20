package com.example.jpmc.network

// Function to return user understandable error messages based on http error codes
fun getErrorMessages(responseCode: Int): String {
    return when(responseCode) {
        in 400..499 -> " Bad request, client error."
        in 300..399 -> " Temporarily unavailable, try later."
        in 500..599 -> " Internal server error."
        else -> "Unknown error occurred."
    }
}