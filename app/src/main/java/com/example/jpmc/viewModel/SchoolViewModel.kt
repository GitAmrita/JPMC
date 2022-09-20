package com.example.jpmc.viewModel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpmc.model.NYCSchool
import com.example.jpmc.network.NetworkResult
import com.example.jpmc.repository.SchoolRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel(private val repo: SchoolRepo): ViewModel() {
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

    // Workarounds made in the interest of expediency:
    // Could not use android TextUtils utilities for checking isEmpty or IsDigit due to unit test
    // constraints using junit.
    // Didn't use robolectric tests since it slows down the running of tests as it loads the
    // android env before running tests
    fun getFilteredSchoolList(filter: String, schools: List<NYCSchool>): List<NYCSchool> {
        if (filter.length == 0) return schools
        return try {
            filter.toInt()
            schools.filter { s -> s.zip == filter }
        } catch (e: Exception) {
            schools.filter { s -> s.city.contains(filter, ignoreCase = true) }
        }
    }

//    private fun getContent():ArrayList<NYCSchool> {
//        val list = ArrayList<NYCSchool>()
//        list.add(NYCSchool("abc", "123", "sc", "111", "aaa"))
//        list.add(NYCSchool("nju", "45", "ol", "134", "lll"))
//        list.add(NYCSchool("kilop", "123786543", "scoplo", "11154637", "hhh"))
//        return list
//    }
}