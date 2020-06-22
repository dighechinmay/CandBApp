package com.chinmay.testapp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

class TestDataModel {

    data class Response(

        val tests: ArrayList<Tests>
    )


    data class Tests(

        val title: String,
        val description: String,
        val eventDate: Long,
        val createdDate: Long,
        val standard: String,
        val section: String
    )

    }
