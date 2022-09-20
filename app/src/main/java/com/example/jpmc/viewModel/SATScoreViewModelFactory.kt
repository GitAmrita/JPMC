package com.example.jpmc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jpmc.repository.SATScoreRepo

class SATScoreViewModelFactory(private val repo: SATScoreRepo): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return SATScoreViewModel(repo) as T
    }
}