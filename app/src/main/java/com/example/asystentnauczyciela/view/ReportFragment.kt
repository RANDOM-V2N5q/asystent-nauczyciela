package com.example.asystentnauczyciela

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.view_model.Adapters.StudentsMarksListAdapter
import com.example.asystentnauczyciela.view_model.ReportViewModel
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModel: ReportViewModel
/**
 * A simple [Fragment] subclass.
 * Use the [ReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myAdapter: StudentsMarksListAdapter
    private lateinit var myLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProvider(requireActivity()).get(ReportViewModel::class.java)

        val time = Calendar.getInstance()
        time.time = Date()

        time[Calendar.HOUR_OF_DAY] = 0
        time[Calendar.MINUTE] = 0
        time[Calendar.SECOND] = 0
        time[Calendar.MILLISECOND] = 0
        val beginDate = Date(time.timeInMillis)

        time[Calendar.HOUR_OF_DAY] = 23
        time[Calendar.MINUTE] = 59
        time[Calendar.SECOND] = 59
        time[Calendar.MILLISECOND] = 999
        val endDate = Date(time.timeInMillis)

        viewModel.Marks(beginDate, endDate)

        myAdapter = StudentsMarksListAdapter(viewModel.marks)
        myLayoutManager = LinearLayoutManager(context)

        viewModel.marks.observe(viewLifecycleOwner, {
            myAdapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerViewStudentsMarks.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReportFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}