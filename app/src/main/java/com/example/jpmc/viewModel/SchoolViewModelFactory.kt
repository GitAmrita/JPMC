package com.example.jpmc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jpmc.repository.SchoolRepo
import com.example.jpmc.repository.SchoolRepoImpl

class SchoolViewModelFactory(private val repo: SchoolRepo): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return SchoolViewModel(repo) as T
    }
}