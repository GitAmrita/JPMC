package com.example.jpmc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.jpmc.databinding.ItemNycSchoolBinding
import com.example.jpmc.model.NYCSchool

const val SCHOOL_ID = "schoolID"
const val SCHOOL_NAME = "schoolName"
const val SCHOOL_PHONE = "schoolPhone"
const val SCHOOL_EMAIL = "schoolEmail"
const val SCHOOL_WEBSITE = "schoolWebsite"
// Adapter for recycle view displaying the list of school in the first screen
class NYCSchoolAdapter(private val schoolList: List<NYCSchool>) :
    RecyclerView.Adapter<NYCSchoolAdapter.NYCSchoolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NYCSchoolViewHolder {
        val binding = ItemNycSchoolBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)
        return NYCSchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NYCSchoolViewHolder, position: Int) {
        holder.bind(schoolList[position])
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    inner class NYCSchoolViewHolder(
        private val binding: ItemNycSchoolBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(school: NYCSchool) {
            binding.name.text = school.name
            binding.address.text = school.address
            binding.city.text = school.city
            binding.zip.text = school.zip

            binding.schoolCardItem.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(SCHOOL_ID , school.id)
                bundle.putString(SCHOOL_NAME , school.name)
                bundle.putString(SCHOOL_PHONE , school.phoneNo)
                bundle.putString(SCHOOL_EMAIL , school.email)
                bundle.putString(SCHOOL_WEBSITE , school.website)
                Navigation.findNavController(it).navigate(
                    R.id.action_NYCSchool_to_SATScore_fragment, bundle)
            }
        }
    }
}