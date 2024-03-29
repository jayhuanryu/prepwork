package com.example.moviedb.data_models

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviedb.Consts
import com.squareup.picasso.Picasso

@Entity(tableName = "main_result_table")
class MainResultsDataModel(
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "voteCount") val vote_count: Int,
    val video: Boolean,
    val poster_path: String?,
    @PrimaryKey val id: Int,
    val adult: Boolean?,
    val backdrop_path: Boolean?,
    val original_language: String?,
    val original_title: String?,
    //    val genre_ids : List<Int>?,
    @ColumnInfo(name = "title") val title: String?,
    val vote_average: Float,
    val overview: String?,
    val release_date: String?,
    var isLiked : Boolean,
    var runtime : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(popularity)
        parcel.writeInt(vote_count)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeString(poster_path)
        parcel.writeInt(id)
        parcel.writeValue(adult)
        parcel.writeValue(backdrop_path)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(title)
        parcel.writeFloat(vote_average)
        parcel.writeString(overview)
        parcel.writeString(release_date)
        parcel.writeByte(if (isLiked) 1 else 0)
        parcel.writeInt(runtime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainResultsDataModel> {
        override fun createFromParcel(parcel: Parcel): MainResultsDataModel {
            return MainResultsDataModel(parcel)
        }

        override fun newArray(size: Int): Array<MainResultsDataModel?> {
            return arrayOfNulls(size)
        }

        @JvmStatic
        @BindingAdapter("app:setImageResource")
        fun setImageResource(imageView: ImageView, poster_path: String?) {
            Picasso.get().load(Consts.BASE_IMAGE_URL + "w185/" + poster_path).into(imageView)
//        Picasso.get().load()
        }

        @JvmStatic
        @BindingAdapter("app:setRatingText")
        fun setRatingText(textView: TextView, rating : Float) {
            textView.text = String.format("%s / 10.0", rating.toString())
        }

        @JvmStatic
        @BindingAdapter("app:setRunTimeText")
        fun setRunTimeText(textView: TextView, runtime: Int) {
            textView.text = String.format("%d Mins", runtime)
        }

        @JvmStatic
        @BindingAdapter("app:setReleaseDate")
        fun setReleaseDataText(textView: TextView, release_date: String?) {
            textView.text = String.format("Release Date : %s", release_date)
        }
    }



}