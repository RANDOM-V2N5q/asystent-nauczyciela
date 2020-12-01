package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Group
import com.example.asystentnauczyciela.view_model.GroupListAdapter
import com.example.asystentnauczyciela.view_model.GroupViewModel
import kotlinx.android.synthetic.main.fragment_all_groups.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var groupViewModel: GroupViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [GroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: GroupListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        groupViewModel = ViewModelProvider(requireActivity()).get(GroupViewModel::class.java)

        myAdapter = GroupListAdapter(groupViewModel.allGroups, groupViewModel)
        myLayoutManager = LinearLayoutManager(context)

        groupViewModel.allGroups.observe(viewLifecycleOwner, {
            myAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerViewGroups.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        buttonAddGroup.setOnClickListener { addGroup() }
    }

    private fun addGroup() {
        val groupName = editTextGroupName.text.toString()
        editTextGroupName.setText("")

        if(inputValidation(groupName)) {
            val group = Group(0, groupName)
            groupViewModel.addGroup(group)
            Toast.makeText(requireContext(), "Grupa dodana", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputValidation(groupName: String): Boolean {
        return !TextUtils.isEmpty(groupName)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}