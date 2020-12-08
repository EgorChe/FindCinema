package ru.course.findcinema

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val name: String, val year: String) : Parcelable