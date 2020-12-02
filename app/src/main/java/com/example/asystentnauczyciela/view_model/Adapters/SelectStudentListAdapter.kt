package com.example.asystentnauczyciela.view_model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.view_model.AddStudentToGroupViewModel

class SelectStudentListAdapter(var students: LiveData<List<Student>>, var viewModel: AddStudentToGroupViewModel): RecyclerView.Adapter<SelectStudentListAdapter.SelectedStudentHolder>() {

    inner class SelectedStudentHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedStudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.select_student_row, parent, false)
        return SelectedStudentHolder(view)
    }

    override fun onBindViewHolder(holder: SelectedStudentHolder, position: Int) {
        //TODO: change R.id.textViewFirstName? Collision with student_row
        val firstName = holder.itemView.findViewById<TextView>(R.id.textViewFirstName)
        val lastName = holder.itemView.findViewById<TextView>(R.id.textViewLastName)
        val check = holder.itemView.findViewById<ImageView>(R.id.imageViewCheck)

        firstName.text = students.value?.get(position)?.firstName.toString()
        lastName.text = students.value?.get(position)?.lastName.toString()

        holder.itemView.setOnClickListener {
            if(viewModel.isSelected(students.value?.get(position)?.id!!)) {
                viewModel.deselect(students.value?.get(position)?.id!!)
                check.visibility = View.INVISIBLE
            }
            else {
                viewModel.select(students.value?.get(position)?.id!!)
                check.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return students.value?.size ?: 0
    }
}