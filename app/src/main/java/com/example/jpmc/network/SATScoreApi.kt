package com.example.jpmc.network

import com.example.jpmc.model.SATScore
import retrofit2.Response
import retrofit2.http.GET

interface SATScoreApi {
    @GET("resource/f9bf-2cp4.json")
    suspend fun getSATScores(): Response<List<SATScore>>
}