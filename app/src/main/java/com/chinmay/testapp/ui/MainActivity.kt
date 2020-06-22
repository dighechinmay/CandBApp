package com.chinmay.testapp.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.chinmay.testapp.BASE_URL
import com.chinmay.testapp.R
import com.chinmay.testapp.inTransactionOne
import com.chinmay.testapp.inTransactionTwo
import com.chinmay.testapp.models.TestDataModel
import com.chinmay.testapp.network.TestApi
import com.chinmay.testapp.ui.fragments.FirstFragment
import com.chinmay.testapp.ui.fragments.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val firstFragment = FirstFragment()
//
//        supportFragmentManager.inTransactionOne {
//            add(R.id.fragment_container, firstFragment) }


        generateService()


    }

    private fun generateService() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(TestApi::class.java)
        val call = service.getTests("school")



        call.enqueue(object: Callback<List<TestDataModel.Response>>{
            override fun onFailure(call: Call<List<TestDataModel.Response>>, t: Throwable) {
                Log.i("error",t.message)
            }

            override fun onResponse(
                call: Call<List<TestDataModel.Response>>,
                response: Response<List<TestDataModel.Response>>
            ) {
                Log.i("response",response.body()!!.size.toString())
            }
        })

    }

    fun switchFragmentTwo(view: View){

    }



    fun switchFragmentOne(view: View){

        val secondFragment = SecondFragment()

        supportFragmentManager.inTransactionTwo {
            add(R.id.fragment_container, secondFragment)
        }

        button_first.setBackgroundResource(R.drawable.button_not_selected)
        button_second.setBackgroundResource(R.drawable.button_switch)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
