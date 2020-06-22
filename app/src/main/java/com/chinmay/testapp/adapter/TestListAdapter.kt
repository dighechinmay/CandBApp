package com.chinmay.testapp.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.testapp.models.TestDataModel


class TestListAdapter(context: Context,private val testList:ArrayList<TestDataModel.Tests>): RecyclerView.Adapter<TestListAdapter.ListViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_list_item_1, parent, false)
        return ListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return testList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

    }

    class ListViewHolder(v: View):RecyclerView.ViewHolder(v){

    }
}