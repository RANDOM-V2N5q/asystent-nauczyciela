package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.Adapters.DeleteStudentFromGroupListAdapter
import com.example.asystentnauczyciela.view_model.DeleteStudentsFromGroupViewModel
import kotlinx.android.synthetic.main.fragment_delete_students_from_group.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: DeleteStudentsFromGroupViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [DeleteStudentsFromGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeleteStudentsFromGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: DeleteStudentFromGroupListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    val args: DeleteStudentsFromGroupFragmentArgs by navArgs()

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
        viewModel = ViewModelProvider(requireActivity()).get(DeleteStudentsFromGroupViewModel::class.java)
        viewModel.studentsInGroup(args.groupId)

        myAdapter = DeleteStudentFromGroupListAdapter(viewModel.studentsInGroup, viewModel)
        myLayoutManager = LinearLayoutManager(context)

        viewModel.studentsInGroup.observe(viewLifecycleOwner, {
            myAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_students_from_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerViewSelectStudent.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        buttonDeleteStudentFromGroup.setOnClickListener { deleteStudentFromGroup() }
    }

    private fun deleteStudentFromGroup() {
        viewModel.deleteSelectedStudents(args.groupId)
        findNavController().navigate(R.id.action_deleteStudentsFromGroupFragment_to_studentsInGroupFragment)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment deleteStudentsFromGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeleteStudentsFromGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}