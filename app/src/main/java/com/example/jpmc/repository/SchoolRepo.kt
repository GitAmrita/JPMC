package com.example.jpmc.repository

import com.example.jpmc.model.NYCSchool
import com.example.jpmc.network.NetworkResult

interface SchoolRepo {

    suspend fun getSchools(): NetworkResult<List<NYCSchool>>
}