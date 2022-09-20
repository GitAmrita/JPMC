package com.example.jpmc.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpmc.model.SATScore
import com.example.jpmc.network.NetworkResult
import com.example.jpmc.repository.SATScoreRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SATScoreViewModel(private val repo: SATScoreRepo) : ViewModel() {

    val satScoreObserver: LiveData<SATScore>
    get() = mutableLiveDataScores
    private val mutableLiveDataScores = MutableLiveData<SATScore>()

    val satScoreErrorObserver: LiveData<String>
        get() = mutableLiveDataScoresError
    private val mutableLiveDataScoresError = MutableLiveData<String>()

    fun getSATScores(schoolId: String) {
        viewModelScope.launch(Dispatchers.Main) {
            when (val response = repo.getSATScores()) {
                is NetworkResult.Success -> getSATScoreForSchool(schoolId, response.data!!)
                is NetworkResult.Error -> mutableLiveDataScoresError.value = response.message!!
            }
        }
    }

    fun getSATScoreForSchool(schoolId: String, scores: List<SATScore>) {
        val filteredScore = scores.filter { s -> s.schoolId == schoolId }
        if (filteredScore.size > 0)
            mutableLiveDataScores.value = filteredScore.first()
        else
            mutableLiveDataScoresError.value = "SAT score data is unavailable for this school."
    }
}