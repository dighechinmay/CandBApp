package com.chinmay.testapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.chinmay.testapp.BASE_URL
import com.chinmay.testapp.R
import com.chinmay.testapp.adapter.TestListAdapter
import com.chinmay.testapp.models.TestDataModel
import com.chinmay.testapp.network.TestApi
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: TestListAdapter

    companion object {
        fun newInstance(myList : ArrayList<TestDataModel.Tests>): FirstFragment {
            val args = Bundle()
            args.putParcelableArrayList("list",myList);
            val fragment = FirstFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var list = arguments?.getParcelableArrayList<TestDataModel.Tests>("list")

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        linearLayoutManager = LinearLayoutManager(context)
        view.recyclerView1.layoutManager = linearLayoutManager
        adapter = TestListAdapter(list as ArrayList<TestDataModel.Tests>)
        view.recyclerView1.adapter = adapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        view.findViewById<Button>(R.id.button_first).setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }



}
