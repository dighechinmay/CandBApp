package com.chinmay.testapp.models

import android.os.Parcel
import android.os.Parcelable
import java.util.*
import kotlin.collections.ArrayList

class TestDataModel {

//    data class Response(
//
//        val tests: ArrayList<Tests>
//    )


    data class Tests(

        val title: String,
        val descrition: String,
        val eventDate: Long,
        val createdDate: Long,
        val standard: String,
        val section: String
    ):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString()!!,
            parcel.readString()!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(title)
            parcel.writeString(descrition)
            parcel.writeLong(eventDate)
            parcel.writeLong(createdDate)
            parcel.writeString(standard)
            parcel.writeString(section)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Tests> {
            override fun createFromParcel(parcel: Parcel): Tests {
                return Tests(parcel)
            }

            override fun newArray(size: Int): Array<Tests?> {
                return arrayOfNulls(size)
            }
        }
    }

}
