package com.chinmay.testapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chinmay.testapp.R
import com.chinmay.testapp.models.TestDataModel
import com.chinmay.testapp.network.TestApi
import com.chinmay.testapp.ui.fragments.TestListDisplayFragment
import com.chinmay.testapp.util.BASE_URL
import com.chinmay.testapp.util.inTransaction
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var  mListOfFirstFragment: ArrayList<TestDataModel.Tests>
    lateinit var  mListOfSecondFragment: ArrayList<TestDataModel.Tests>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getTestListFromApi(this)

    }

    private fun getTestListFromApi(mAppContext: Context) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TestApi::class.java)
        var call = service.getTests("school")



        call.enqueue(object: Callback<ArrayList<TestDataModel.Tests>>{
            override fun onFailure(call: Call<ArrayList<TestDataModel.Tests>>, t: Throwable) {
                Toast.makeText(mAppContext,"Something went wrong, try again!",Toast.LENGTH_LONG).show()
                Log.i("error",t.message)
            }

            override fun onResponse(
                call: Call<ArrayList<TestDataModel.Tests>>,
                response: Response<ArrayList<TestDataModel.Tests>>
            ) {

                progressBar.visibility = View.INVISIBLE
                mListOfFirstFragment = response.body()!!
                replaceFragment(response.body()!!)


                val call2 = service.getTests("class")

                call2.enqueue(object: Callback<ArrayList<TestDataModel.Tests>>{
                    override fun onFailure(call: Call<ArrayList<TestDataModel.Tests>>, t: Throwable) {
                        Toast.makeText(mAppContext,"Something went wrong, try again!",Toast.LENGTH_LONG).show()
                        Log.i("error",t.message)
                    }

                    override fun onResponse(
                        call: Call<ArrayList<TestDataModel.Tests>>,
                        response: Response<ArrayList<TestDataModel.Tests>>
                    ) {
                        classlevel_textview.setTextColor(ContextCompat.getColor(mAppContext,R.color.deselected))
                        mListOfSecondFragment = response.body()!!

                        setClickListeners()
                      }
                })


            }
        })

    }

    private fun setClickListeners() {

        button_first.setOnClickListener{


                replaceFragment(mListOfFirstFragment)

            button_first.setBackgroundResource(R.drawable.button_selected)
            schoollevel_textview.setTextColor(ContextCompat.getColor(this,R.color.selected))
            button_second.setBackgroundResource(R.drawable.button_not_selected)
            classlevel_textview.setTextColor(ContextCompat.getColor(this,R.color.deselected))

        }

        button_second.setOnClickListener {

            replaceFragment(mListOfSecondFragment)

            button_first.setBackgroundResource(R.drawable.button_not_selected)
            schoollevel_textview.setTextColor(ContextCompat.getColor(this,R.color.deselected))
            button_second.setBackgroundResource(R.drawable.button_selected)
            classlevel_textview.setTextColor(ContextCompat.getColor(this,R.color.selected))
        }

    }


    fun replaceFragment(list: ArrayList<TestDataModel.Tests>){

        val fragment = TestListDisplayFragment.newInstance(list)
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, fragment) }
    }



}
