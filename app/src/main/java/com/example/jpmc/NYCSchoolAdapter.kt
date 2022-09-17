package com.example.jpmc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jpmc.databinding.ItemNycSchoolBinding
import com.example.jpmc.model.NYCSchool

class NYCSchoolAdapter(private val schoolList: List<NYCSchool>) : RecyclerView.Adapter<NYCSchoolAdapter.NYCSchoolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NYCSchoolViewHolder {
        val binding = ItemNycSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NYCSchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NYCSchoolViewHolder, position: Int) {
        holder.bind(schoolList[position])
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    inner class NYCSchoolViewHolder(private val binding: ItemNycSchoolBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(school: NYCSchool) {
            binding.name.text = school.name
            binding.address.text = school.address
            binding.city.text = school.city
            binding.zip.text = school.zip
        }
    }
}