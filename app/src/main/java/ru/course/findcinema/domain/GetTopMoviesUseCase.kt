package ru.course.findcinema.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.course.findcinema.data.network.MoviesApi

class GetTopMoviesUseCase(private val moviesApi: MoviesApi) {

    suspend operator fun invoke(): List<Movie> = withContext(Dispatchers.IO) {
        moviesApi.getTopFilms(page = 1).run {
            films?.mapNotNull {
                Movie(
                    name = it.nameRu ?: return@mapNotNull null,
                    year = it.year ?: return@mapNotNull null
                )
            } ?: emptyList()
        }
    }
}