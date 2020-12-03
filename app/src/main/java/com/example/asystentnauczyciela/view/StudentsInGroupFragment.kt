package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.Adapters.StudentInGroupListAdapter
import com.example.asystentnauczyciela.view_model.AllStudentsViewModel
import com.example.asystentnauczyciela.view_model.StudentsInGroupViewModel
import kotlinx.android.synthetic.main.fragment_students_in_group.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: StudentsInGroupViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [StudentsInGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentsInGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: StudentInGroupListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    val args: StudentsInGroupFragmentArgs by navArgs()

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
        viewModel = ViewModelProvider(requireActivity()).get(StudentsInGroupViewModel::class.java)
        viewModel.studentsInGroup(args.groupId)
        
        myAdapter = StudentInGroupListAdapter(viewModel.studentsInGroup, args.groupId)
        myLayoutManager = LinearLayoutManager(context)

        setHasOptionsMenu(true)

        viewModel.studentsInGroup.observe(viewLifecycleOwner, {
            myAdapter.notifyDataSetChanged()
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students_in_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.students_in_group)

        recyclerView = recyclerViewStudentsInGroup.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuButtonDelete) {
            val action = StudentsInGroupFragmentDirections.actionStudentsInGroupFragmentToDeleteStudentsFromGroupFragment(args.groupId)
            findNavController().navigate(action)
        }
        else if(item.itemId == R.id.menuButtonAdd) {
            val action = StudentsInGroupFragmentDirections.actionStudentsInGroupFragmentToAddStudentToGroupFragment(args.groupId)
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentsInGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentsInGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}