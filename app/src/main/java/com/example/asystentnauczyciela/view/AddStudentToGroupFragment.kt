package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.Adapters.SelectStudentListAdapter
import com.example.asystentnauczyciela.view_model.Adapters.StudentListAdapter
import com.example.asystentnauczyciela.view_model.AddStudentToGroupViewModel
import com.example.asystentnauczyciela.view_model.AllStudentsViewModel
import kotlinx.android.synthetic.main.fragment_add_student_to_group.*
import kotlinx.android.synthetic.main.fragment_all_students.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var addStudentToGroupViewModel: AddStudentToGroupViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentToGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddStudentToGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: SelectStudentListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    val args: AddStudentToGroupFragmentArgs by navArgs()

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
        addStudentToGroupViewModel = ViewModelProvider(requireActivity()).get(AddStudentToGroupViewModel::class.java)

        myAdapter = SelectStudentListAdapter(addStudentToGroupViewModel.allStudents, addStudentToGroupViewModel)
        myLayoutManager = LinearLayoutManager(context)

        addStudentToGroupViewModel.allStudents.observe(viewLifecycleOwner, {
            myAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student_to_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerViewSelectStudent.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        buttonAddSelectedStudent.setOnClickListener { addSelectedStudent() }
    }

    private fun addSelectedStudent() {
        addStudentToGroupViewModel.addSelectedStudents(args.groupId)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddStudentToGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddStudentToGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}