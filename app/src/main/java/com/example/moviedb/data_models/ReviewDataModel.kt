package com.example.moviedb.data_models

import android.os.Parcel
import android.os.Parcelable

data class ReviewDataModel(
    val page : Int?,
    val id : Int?,
    val result : List<ReviewResultDataModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("result")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(page)
        parcel.writeValue(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewDataModel> {
        override fun createFromParcel(parcel: Parcel): ReviewDataModel {
            return ReviewDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ReviewDataModel?> {
            return arrayOfNulls(size)
        }
    }
}
