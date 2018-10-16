package com.example.android.wallpapers.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallpaper(val creator: String, val pictureURl: String): Parcelable {
    constructor(): this("","")
}