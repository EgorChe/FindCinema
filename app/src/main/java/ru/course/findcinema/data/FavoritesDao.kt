package ru.course.findcinema.data

import ru.course.findcinema.Movie

interface FavoritesDao {

    /**
     * отдаёт все избранные фильмы
     * */
    fun getAll(): List<Movie>

    /**
     * добавляет фильм в избранное
     * */
    fun add(movie: Movie)

    /**
     * удаляет [movie] из избранного
     * */
    fun delete(movie: Movie)

    /**
     * @return true если фильм в избранном. иначе false
     * */
    fun isInFavorites(movie: Movie): Boolean
}

