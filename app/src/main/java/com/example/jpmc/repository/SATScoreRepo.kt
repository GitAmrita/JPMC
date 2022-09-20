package com.example.jpmc.repository

import com.example.jpmc.model.SATScore
import com.example.jpmc.network.NetworkResult

interface SATScoreRepo {
    suspend fun getSATScores(): NetworkResult<List<SATScore>>
}