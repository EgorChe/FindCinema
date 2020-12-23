package ru.course.findcinema.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.course.findcinema.di.MoviesApi
import javax.inject.Inject

class GetTopMoviesUseCase @Inject constructor(
    private val moviesApi: MoviesApi
) {

    suspend operator fun invoke(): List<Movie> = withContext(Dispatchers.IO) {
        moviesApi.getTopFilms(page = 1).run {
            films?.mapNotNull { film ->
                Movie(
                    name = film.nameRu ?: return@mapNotNull null,
                    year = film.year ?: return@mapNotNull null
                )
            } ?: emptyList()
        }
    }
}