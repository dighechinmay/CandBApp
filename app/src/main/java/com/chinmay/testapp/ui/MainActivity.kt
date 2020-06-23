package com.chinmay.testapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.chinmay.testapp.BASE_URL
import com.chinmay.testapp.R
import com.chinmay.testapp.inTransactionOne
import com.chinmay.testapp.models.TestDataModel
import com.chinmay.testapp.network.TestApi
import com.chinmay.testapp.ui.fragments.FirstFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var  listForSecondFragment: ArrayList<TestDataModel.Tests>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getTestListFromApi()


    }

    private fun getTestListFromApi() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TestApi::class.java)
        var call = service.getTests("school")



        call.enqueue(object: Callback<ArrayList<TestDataModel.Tests>>{
            override fun onFailure(call: Call<ArrayList<TestDataModel.Tests>>, t: Throwable) {
                Log.i("error",t.message)
            }

            override fun onResponse(
                call: Call<ArrayList<TestDataModel.Tests>>,
                response: Response<ArrayList<TestDataModel.Tests>>
            ) {

                //Log.i("message",response.body()!!.size.toString())
                progressBar.visibility = View.INVISIBLE

                val fragment = FirstFragment.newInstance(response.body()!!)
                supportFragmentManager.inTransactionOne {
                add(R.id.fragment_container, fragment) }

                val call2 = service.getTests("class")

                call2.enqueue(object: Callback<ArrayList<TestDataModel.Tests>>{
                    override fun onFailure(call: Call<ArrayList<TestDataModel.Tests>>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<ArrayList<TestDataModel.Tests>>,
                        response: Response<ArrayList<TestDataModel.Tests>>
                    ) {
                        listForSecondFragment = response.body()!!
                      }
                })


            }
        })

    }

 /*   fun gotoFragmentOne(view: View){

        supportFragmentManager.popBackStack()

        button_first.setBackgroundResource(R.drawable.button_switch)
        school_button.setTextColor(ContextCompat.getColor(this,R.color.selected))
        button_second.setBackgroundResource(R.drawable.button_not_selected)
        class_button.setTextColor(ContextCompat.getColor(this,R.color.deselected))

    }



    fun gotoFragmentTwo(view: View){

        val fragment = SecondFragment.newInstance(listForSecondFragment)
        supportFragmentManager.inTransactionOne {
            replace(R.id.fragment_container, fragment) }

        button_first.setBackgroundResource(R.drawable.button_not_selected)
        school_button.setTextColor(ContextCompat.getColor(this,R.color.deselected))
        button_second.setBackgroundResource(R.drawable.button_switch)
        class_button.setTextColor(ContextCompat.getColor(this,R.color.selected))

    }*/


}
