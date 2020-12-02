package com.example.asystentnauczyciela.view_model.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Group
import com.example.asystentnauczyciela.view_model.AllGroupsViewModel

class GroupListAdapter(var groups: LiveData<List<Group>>, var groupViewModel: AllGroupsViewModel): RecyclerView.Adapter<GroupListAdapter.GroupHolder>() {

    inner class GroupHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_row, parent, false)
        return GroupHolder(view)
    }

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        var groupName = holder.itemView.findViewById<TextView>(R.id.textViewGroupName)
        var deleteButton = holder.itemView.findViewById<Button>(R.id.buttonDeleteGroup)

        groupName.text = groups.value?.get(position)?.name.toString()
        deleteButton.setOnClickListener { groupViewModel.deleteGroup(groups.value?.get(position)!!) }
    }

    override fun getItemCount(): Int {
        return groups.value?.size ?: 0
    }
}