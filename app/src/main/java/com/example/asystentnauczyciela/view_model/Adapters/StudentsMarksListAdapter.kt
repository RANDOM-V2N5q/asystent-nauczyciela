package com.example.asystentnauczyciela.view_model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.StudentMarks
import java.text.SimpleDateFormat

class StudentsMarksListAdapter(var marks: LiveData<List<StudentMarks>>): RecyclerView.Adapter<StudentsMarksListAdapter.Holder>() {

    inner class Holder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mark_row_report, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val studentName = holder.itemView.findViewById<TextView>(R.id.textViewReportStudentName)
        val date = holder.itemView.findViewById<TextView>(R.id.textViewReportDate)
        val note = holder.itemView.findViewById<TextView>(R.id.textViewReportMarkNote)
        val value = holder.itemView.findViewById<TextView>(R.id.textViewReportMarkValue)

        studentName.text = marks.value?.get(position)?.student?.firstName + " " + marks.value?.get(position)?.student?.lastName
        date.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(marks.value?.get(position)?.mark?.date)
        note.text = marks.value?.get(position)?.mark?.note ?: ""
        value.text = marks.value?.get(position)?.mark?.value.toString()
    }

    override fun getItemCount(): Int {
        return marks.value?.size ?: 0
    }
}