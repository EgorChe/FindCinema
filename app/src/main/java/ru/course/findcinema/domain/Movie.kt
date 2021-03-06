package ru.course.findcinema.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(val name: String, val year: String) : Parcelable
