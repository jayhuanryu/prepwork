package com.example.moviedb.data_models

import android.os.Parcel
import android.os.Parcelable

data class ReviewResultDataModel(
    val author : String?,
    val content : String?,
    val id : String?,
    val url : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(content)
        parcel.writeString(id)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewResultDataModel> {
        override fun createFromParcel(parcel: Parcel): ReviewResultDataModel {
            return ReviewResultDataModel(parcel)
        }

        override fun newArray(size: Int): Array<ReviewResultDataModel?> {
            return arrayOfNulls(size)
        }
    }
}

