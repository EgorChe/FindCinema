package ru.course.findcinema.data

import android.content.SharedPreferences
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.course.findcinema.Movie

class FavoritesDaoImpl(private val preferences: SharedPreferences) : FavoritesDao {

    private var movies: List<Movie>
        get() = preferences.getString(FAVORITES_MOVIES_KEY, null)?.let {
            try {
                Json.decodeFromString<List<Movie>>(it)
            } catch (t: Throwable) {
                emptyList()
            }
        } ?: emptyList()
        set(value) {
            preferences.edit().putString(FAVORITES_MOVIES_KEY, Json.encodeToString(value)).apply()
        }

    override fun getAll(): List<Movie> = movies

    override fun add(movie: Movie) {
        movies = (movies + movie).distinct()
    }

    override fun delete(movie: Movie) {
        movies = movies.filter { it == movie }
    }

    override fun isInFavorites(movie: Movie): Boolean = movies.contains(movie)

    companion object {

        private const val FAVORITES_MOVIES_KEY = "FAVORITES_MOVIES_KEY"
    }
}