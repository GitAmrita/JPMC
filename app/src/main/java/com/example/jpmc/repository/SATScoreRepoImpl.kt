package com.example.jpmc.repository

import com.example.jpmc.model.SATScore
import com.example.jpmc.network.NetworkResult
import com.example.jpmc.network.RetrofitClient.getRetrofit
import com.example.jpmc.network.SATScoreApi
import com.example.jpmc.network.getErrorMessages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SATScoreRepoImpl: SATScoreRepo {
    private val api: SATScoreApi = getRetrofit().create(SATScoreApi::class.java)

    override suspend fun getSATScores(): NetworkResult<List<SATScore>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getSATScores()
                if (response.isSuccessful) {
                    response.body() ?.let {
                        NetworkResult.Success(data = it)
                    } ?: NetworkResult.Error("get SAT score message body is empty")
                } else {
                    NetworkResult.Error(getErrorMessages(response.code()))
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message)
            }
        }
    }
}