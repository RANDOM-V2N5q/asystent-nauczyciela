package com.example.asystentnauczyciela.view_model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Group
import com.example.asystentnauczyciela.view.MarkFragmentArgs
import com.example.asystentnauczyciela.view.StudentGroupsFragmentDirections
import com.example.asystentnauczyciela.view.StudentsInGroupFragmentDirections

class StudentGroupsListAdapter(var groups: LiveData<List<Group>>, var studentId: Int): RecyclerView.Adapter<StudentGroupsListAdapter.Holder>() {

    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_group_row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val groupName = holder.itemView.findViewById<TextView>(R.id.textViewGroupName)

        groupName.text = groups.value?.get(position)?.name.toString()

        holder.itemView.setOnClickListener {
            val action = StudentGroupsFragmentDirections.actionStudentGroupsFragmentToMarkFragment(groups.value?.get(position)?.id!!, studentId)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return groups.value?.size ?: 0
    }
}