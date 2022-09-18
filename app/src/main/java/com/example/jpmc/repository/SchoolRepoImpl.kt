package com.example.jpmc.repository

import com.example.jpmc.model.NYCSchool
import com.example.jpmc.network.SchoolApi
import com.example.jpmc.network.NetworkResult
import com.example.jpmc.network.RetrofitClient.getRetrofit
import com.example.jpmc.network.getErrorMessages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class SchoolRepoImpl : SchoolRepo {
    private val api : SchoolApi = getRetrofit().create(SchoolApi::class.java)

    override suspend fun getSchools(): NetworkResult<List<NYCSchool>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getSchools()
                if (response.isSuccessful) {
                    response.body()?. let {
                        NetworkResult.Success(it)
                    } ?: NetworkResult.Error("message body is empty")
                } else {
                    NetworkResult.Error(getErrorMessages(response.code()))
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message)
            }
        }
    }
}