package com.chinmay.testapp.network

import com.chinmay.testapp.models.TestDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TestApi {


    @GET("{extension}")
    fun getTests(@Path("extension") type: String): Call<ArrayList<TestDataModel.Tests>>


}