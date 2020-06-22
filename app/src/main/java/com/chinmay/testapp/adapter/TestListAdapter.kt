package com.chinmay.testapp.adapter

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.chinmay.testapp.R
import com.chinmay.testapp.models.TestDataModel
import kotlinx.android.synthetic.main.listitem_card_layout.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class TestListAdapter(private val testList:ArrayList<TestDataModel.Tests>): RecyclerView.Adapter<TestListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_card_layout, parent, false)
        return ListViewHolder(view)

    }

    override fun getItemCount(): Int {
        return testList.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val test = testList[position]
        holder.bindTestDetails(test)

    }

    class ListViewHolder(v: View):RecyclerView.ViewHolder(v){

        private var view: View = v

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindTestDetails(test: TestDataModel.Tests){

            view.test_heading.text = test.title
            view.section_textView.text = test.standard+"th "+test.section
            view.createDate_textView.text = formatDate(test.createdDate)
            view.eventDate_textView.text = "On "+ formatDate(test.eventDate)


        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDate(date: Long):String{

            val current = LocalDateTime.now()

            val formatter = DateTimeFormatter.ofPattern("d MMM")
           // val formatter = DateTimeFormatter.ofPattern("h:mm a")

            return current.format(formatter)
        }

    }


}