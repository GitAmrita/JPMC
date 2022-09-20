package com.example.jpmc

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jpmc.model.SATScore
import com.example.jpmc.repository.SATScoreRepo
import com.example.jpmc.viewModel.SATScoreViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock


class SATScoreViewModelTest {
    private val satScoreRepo = mock<SATScoreRepo>()
    private lateinit var viewModel: SATScoreViewModel
    private lateinit var list: List<SATScore>

    @Before
    fun setUp() {
        viewModel = SATScoreViewModel(satScoreRepo)
        list = listOf(
            SATScore("abc", "123", "124", "111", "qqq"),
            SATScore("nju", "45", "789", "134", "jjj"),
            SATScore("kop", "543", "76", "167", "fff")
        )
    }
    // A JUnit Test Rule that swaps the background executor used by the
    // Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `getSATScoreForSchool scores present`() = runBlocking {
        viewModel.getSATScoreForSchool("jjj", list)
        val resource = viewModel.satScoreObserver.value
        Assert.assertNull(viewModel.satScoreErrorObserver.value)
        Assert.assertEquals("nju", resource!!.schoolName)
    }

    @Test
    fun `getSATScoreForSchool scores absent`() = runBlocking {
        viewModel.getSATScoreForSchool("jjjki", list)
        val resource = viewModel.satScoreErrorObserver.value
        Assert.assertNull(viewModel.satScoreObserver.value)
        Assert.assertEquals("SAT score data is unavailable for this school.",
            resource.toString())
    }
}