package com.example.asystentnauczyciela.view_model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.view.StudentsInGroupFragmentDirections

class StudentInGroupListAdapter(var students: LiveData<List<Student>>, var groupId: Int): RecyclerView.Adapter<StudentInGroupListAdapter.Holder>() {

    inner class Holder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.students_in_group_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val firstName = holder.itemView.findViewById<TextView>(R.id.textViewFirstName)
        val lastName = holder.itemView.findViewById<TextView>(R.id.textViewLastName)

        firstName.text = students.value?.get(position)?.firstName.toString()
        lastName.text = students.value?.get(position)?.lastName.toString()

        holder.itemView.setOnClickListener {
            val action = StudentsInGroupFragmentDirections.actionStudentsInGroupFragmentToMarkFragment(groupId, students.value?.get(position)?.studentId!!)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return students.value?.size ?: 0
    }


}