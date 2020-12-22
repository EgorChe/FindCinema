package ru.course.findcinema.di

import retrofit2.http.GET
import retrofit2.http.Query
import ru.course.findcinema.data.entity.TopMoviesResponse

interface MoviesApi {

    @GET("api/v2.2/films/top")
    suspend fun getTopFilms(
        @Query("type") type: String = "TOP_250_BEST_FILMS",
        @Query("page") page: Int
    ): TopMoviesResponse
}