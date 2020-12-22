package ru.course.findcinema.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopMoviesResponse(
    @SerialName("pagesCount")
    val pagesCount: Int?,
    @SerialName("films")
    val films: List<Film>?
)