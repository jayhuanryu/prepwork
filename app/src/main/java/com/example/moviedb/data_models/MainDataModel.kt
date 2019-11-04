package com.example.moviedb.data_models

import android.os.Parcel
import android.os.Parcelable

data class MainDataModel(
    val page: String?,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MainResultsDataModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        TODO("results")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(page)
        parcel.writeInt(total_results)
        parcel.writeInt(total_pages)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainDataModel> {
        override fun createFromParcel(parcel: Parcel): MainDataModel {
            return MainDataModel(parcel)
        }

        override fun newArray(size: Int): Array<MainDataModel?> {
            return arrayOfNulls(size)
        }
    }
}