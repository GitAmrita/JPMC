package com.example.jpmc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jpmc.model.NYCSchool
import com.example.jpmc.network.NetworkResult
import com.example.jpmc.repository.SchoolRepo
import com.example.jpmc.viewModel.SchoolViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class SchoolViewModelTest {
    private val schoolRepo = mock<SchoolRepo>()
    private lateinit var viewModel: SchoolViewModel
    private lateinit var list: List<NYCSchool>

    @Before
    fun setUp() {
        viewModel = SchoolViewModel(schoolRepo)
        list = listOf(NYCSchool("abc", "123", "sc", "111"),
            NYCSchool("nju", "45", "ol", "134"),
            NYCSchool("kilop", "123786543", "scoplo", "11154637")
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    // The reason behind this rule is the lack of Looper.getMainLooper() on the testing environment
    // which is present on a real application. To fix this swap the Main dispatcher
    // with TestCoroutineDispatcher
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // A JUnit Test Rule that swaps the background executor used by the
    // Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `getSchoolList returns success`() = runBlocking {
        whenever(schoolRepo.getSchools()).thenReturn(NetworkResult.Success(list))
        viewModel.getSchoolList()
        val resource = viewModel.schoolListObserver.value
        Assert.assertEquals(3, resource!!.size)
    }

    @Test
    fun `getSchoolList returns error`() = runBlocking {
        whenever(schoolRepo.getSchools()).thenReturn(NetworkResult.Error("test error"))
        viewModel.getSchoolList()
        val resource = viewModel.schoolListErrorObserver.value
        Assert.assertEquals("test error", resource!!.toString())
    }

    @Test
    fun `getFilteredSchoolList test works with city case insensitive`() {
        val result = viewModel.getFilteredSchoolList("SCo", list)
        Assert.assertEquals(1, result.size )
        Assert.assertEquals("kilop", result[0].name )
    }

    @Test
    fun `getFilteredSchoolList test works with zip`() {
        val result = viewModel.getFilteredSchoolList("111", list)
        Assert.assertEquals(1, result.size )
        Assert.assertEquals("abc", result[0].name )
    }

    @Test
    fun `getFilteredSchoolList test works with no filter`() {
        val result = viewModel.getFilteredSchoolList("", list)
        Assert.assertEquals(3, result.size )
    }
}