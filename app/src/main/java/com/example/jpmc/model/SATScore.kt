package com.example.jpmc.model

import com.google.gson.annotations.SerializedName

data class SATScore(
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("sat_critical_reading_avg_score")
    val reading: String,
    @SerializedName("sat_math_avg_score")
    val math: String,
    @SerializedName("sat_writing_avg_score")
    val writing: String,
    @SerializedName("dbn")
    val schoolId: String,
)
