package com.example.jpmc.network

import com.example.jpmc.model.NYCSchool
import retrofit2.Response
import retrofit2.http.GET

interface SchoolApi {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getSchools(): Response<List<NYCSchool>>
}