package com.example.jpmc.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jpmc.*
import com.example.jpmc.databinding.FragmentSatScoresBinding
import com.example.jpmc.repository.SATScoreRepoImpl
import com.example.jpmc.viewModel.SATScoreViewModel
import com.example.jpmc.viewModel.SATScoreViewModelFactory

class SATScoreFragment: Fragment() {
    private val logTag = "SATScoreFragment"

    private lateinit var binding: FragmentSatScoresBinding
    private val schoolId by lazy {
        arguments?.getString(SCHOOL_ID) ?:
        throw Exception ("Missing required argument school id")
    }
    private val schoolName by lazy {
        arguments?.getString(SCHOOL_NAME) ?:
        throw Exception ("Missing required argument school name")
    }
    private val schoolPhone by lazy {
        arguments?.getString(SCHOOL_PHONE)
    }
    private val schoolEmail by lazy {
        arguments?.getString(SCHOOL_EMAIL)
    }
    private val schoolWebsite by lazy {
        arguments?.getString(SCHOOL_WEBSITE)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, SATScoreViewModelFactory(
            SATScoreRepoImpl()))[SATScoreViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSatScoresBinding.inflate(inflater, container, false)
        populateSchoolDetails()
        initializeObservers()
        getSATScores()
        return binding.root

    }

    private fun populateSchoolDetails() {
        binding.schoolName.text = schoolName
        binding.schoolPhone.text = schoolPhone
        binding.schoolEmail.text = schoolEmail
        binding.schoolWebsite.text = schoolWebsite
    }

    private fun initializeObservers() {
        viewModel.satScoreObserver.observe(viewLifecycleOwner, Observer{
            binding.satMathScore.text = it.math
            binding.satReadingScore.text = it.reading
            binding.satWritingScore.text = it.writing
            binding.satScoreGroup.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })

        viewModel.satScoreErrorObserver.observe(viewLifecycleOwner, Observer{
            Log.e(logTag, it)
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun getSATScores() {
        viewModel.getSATScores(schoolId)
    }
}