package com.example.jpmc.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SchoolViewModelFactory: ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return SchoolViewModel() as T
    }
}