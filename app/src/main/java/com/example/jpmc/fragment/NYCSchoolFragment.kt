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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jpmc.NYCSchoolAdapter
import com.example.jpmc.databinding.FragmentNycSchoolBinding
import com.example.jpmc.repository.SchoolRepoImpl
import com.example.jpmc.viewModel.SchoolViewModel
import com.example.jpmc.viewModel.SchoolViewModelFactory

class NYCSchoolFragment: Fragment() {
    private  val logTag = "NYCSchoolFragment"
    private lateinit var binding : FragmentNycSchoolBinding
    private val viewModel by lazy {
        ViewModelProvider(this, SchoolViewModelFactory(
            repo = SchoolRepoImpl()))[SchoolViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNycSchoolBinding.inflate(inflater, container, false)
        initializeRecyclerView()
        initializeObserver()
        getSchools()
        return binding.root
    }

    private fun initializeRecyclerView() {
        binding.schoolRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializeObserver() {
        viewModel.schoolListObserver.observe(viewLifecycleOwner, Observer {
            binding.schoolRecyclerView.adapter = NYCSchoolAdapter(it)
            // todo make it efficient,
            binding.schoolRecyclerView.adapter?.notifyDataSetChanged()
            binding.progressBar.visibility = View.GONE
        })
        viewModel.schoolListErrorObserver.observe(viewLifecycleOwner, Observer {
            // todo move string to res folder
            Log.e(logTag, it)
            binding.progressBar.visibility = View.GONE
            Toast.makeText(requireContext(),
                "Error occurred while retrieving school list",
                Toast.LENGTH_SHORT).show()
        })
    }

    private fun getSchools() {
        viewModel.getSchoolList()
    }
}


