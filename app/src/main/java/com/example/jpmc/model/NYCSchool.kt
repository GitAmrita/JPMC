package com.example.jpmc.model

import com.google.gson.annotations.SerializedName

data class NYCSchool (
    @SerializedName("school_name")
    val name: String,
    @SerializedName("primary_address_line_1")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("zip")
    val zip: String
 )
