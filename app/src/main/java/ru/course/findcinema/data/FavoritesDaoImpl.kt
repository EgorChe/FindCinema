package ru.course.findcinema.data

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.course.findcinema.Movie

class FavoritesDaoImpl(
    private val sharedPreferences: SharedPreferences
) : FavoritesDao {

    private var movies: List<Movie>
        get() = sharedPreferences.getString(FAVORITES_DAO_KEY, null)?.let {
            try {
                Json.decodeFromString(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()
        set(value) {
            sharedPreferences.edit().putString(
                FAVORITES_DAO_KEY,
                Json.encodeToString(value)
            ).apply()
        }

    override fun add(movie: Movie) {
        movies = movies + movie
    }

    override fun delete(movie: Movie) {
        movies = movies.filter { it != movie }
    }

    override fun getAll(): List<Movie> = movies
    override fun isInFavorites(movie: Movie): Boolean = movies.contains(movie)

    companion object {

        private const val FAVORITES_DAO_KEY = "FAVORITES_DAO_KEY"
    }
}