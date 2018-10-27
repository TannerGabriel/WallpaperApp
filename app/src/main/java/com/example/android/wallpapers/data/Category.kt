package com.example.android.wallpapers.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val title: String, val imageUrl: String) : Parcelable {
    constructor(): this("", "")
}