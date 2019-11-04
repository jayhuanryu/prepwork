package com.example.moviedb.data_models

import android.os.Parcel
import android.os.Parcelable

data class TrailerResultDataModel(
    val id : String?,
    val iso_639_1: String?,
    val iso_3166_1 : String?,
    val key : String?,
    val name: String?,
    val site : String?,
    val size : Int,
    val type : String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(iso_639_1)
        parcel.writeString(iso_3166_1)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeInt(size)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrailerResultDataModel> {
        override fun createFromParcel(parcel: Parcel): TrailerResultDataModel {
            return TrailerResultDataModel(parcel)
        }

        override fun newArray(size: Int): Array<TrailerResultDataModel?> {
            return arrayOfNulls(size)
        }
    }
}