package com.example.moviedb.data_models

import android.os.Parcel
import android.os.Parcelable

class DetailDataModel (
    val adult : Boolean,
    val backdrop_path : String?,
    val runtime : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdrop_path)
        parcel.writeInt(runtime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailDataModel> {
        override fun createFromParcel(parcel: Parcel): DetailDataModel {
            return DetailDataModel(parcel)
        }

        override fun newArray(size: Int): Array<DetailDataModel?> {
            return arrayOfNulls(size)
        }
    }
}

//
//{
//    "adult": false,
//    "backdrop_path": "/a1MlbLBk5Sy6YvMbSuKfwGlDVlb.jpg",
//    "belongs_to_collection": {
//    "id": 87118,
//    "name": "Cars Collection",
//    "poster_path": "/uq3N2SFj1Y06zA6LzCQPkmBdaaE.jpg",
//    "backdrop_path": "/yPyhZ5OpYs68Bb8u0PZ1tH0QIgd.jpg"
//},
//    "budget": 120000000,
//    "genres": [
//    {
//        "id": 16,
//        "name": "Animation"
//    },
//    {
//        "id": 12,
//        "name": "Adventure"
//    },
//    {
//        "id": 35,
//        "name": "Comedy"
//    },
//    {
//        "id": 10751,
//        "name": "Family"
//    }
//    ],
//    "homepage": "http://disney.go.com/disneyvideos/animatedfilms/cars/",
//    "id": 920,
//    "imdb_id": "tt0317219",
//    "original_language": "en",
//    "original_title": "Cars",
//    "overview": "Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters.",
//    "popularity": 121.893,
//    "poster_path": "/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg",
//    "production_companies": [
//    {
//        "id": 3,
//        "logo_path": "/1TjvGVDMYsj6JBxOAkUHpPEwLf7.png",
//        "name": "Pixar",
//        "origin_country": "US"
//    },
//    {
//        "id": 2,
//        "logo_path": "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
//        "name": "Walt Disney Pictures",
//        "origin_country": "US"
//    }
//    ],
//    "production_countries": [
//    {
//        "iso_3166_1": "US",
//        "name": "United States of America"
//    }
//    ],
//    "release_date": "2006-06-08",
//    "revenue": 461983149,
//    "runtime": 117,
//    "spoken_languages": [
//    {
//        "iso_639_1": "en",
//        "name": "English"
//    },
//    {
//        "iso_639_1": "it",
//        "name": "Italiano"
//    },
//    {
//        "iso_639_1": "ja",
//        "name": "日本語"
//    },
//    {
//        "iso_639_1": "yi",
//        "name": ""
//    }
//    ],
//    "status": "Released",
//    "tagline": "Ahhh... it's got that new movie smell.",
//    "title": "Cars",
//    "video": false,
//    "vote_average": 6.7,
//    "vote_count": 8306
//}