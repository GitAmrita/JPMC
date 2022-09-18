package com.example.jpmc.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpmc.model.NYCSchool
import com.example.jpmc.network.NetworkResult
import com.example.jpmc.repository.SchoolRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel(private val repo: SchoolRepoImpl): ViewModel() {
    val schoolListObserver: LiveData<List<NYCSchool>>
    get() = mutableSchoolList
    private val mutableSchoolList = MutableLiveData<List<NYCSchool>> ()

    val schoolListErrorObserver: LiveData<String>
        get() = mutableSchoolListError
    private val mutableSchoolListError = MutableLiveData<String> ()

    fun getSchoolList() {
        viewModelScope.launch(Dispatchers.Main) {
            when (val response = repo.getSchools()) {
                is NetworkResult.Success -> mutableSchoolList.value = response.data!!
                is NetworkResult.Error -> mutableSchoolListError.value = response.message!!
            }
        }
    }

    fun getFilteredSchoolList(filter: String, schools: List<NYCSchool>): List<NYCSchool> {
        return if (filter.isDigitsOnly()) {
            schools.filter { s -> s.zip.contains(filter) }
        } else {
            schools.filter { s -> s.city.contains(filter, ignoreCase = true) }
        }
    }

    private fun getContent():ArrayList<NYCSchool> {
        val list = ArrayList<NYCSchool>()
        list.add(NYCSchool("abc", "123", "sc", "111"))
        list.add(NYCSchool("nju", "45", "ol", "134"))
        list.add(NYCSchool("kilop", "123786543", "scoplo", "11154637"))
        return list
    }
}