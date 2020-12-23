package ru.course.findcinema.data

import ru.course.findcinema.domain.Movie

interface FavoritesDao {

    /**
     * добавить [movie] в избранное
     * */
    fun add(movie: Movie)

    /**
     * удаление из избранного
     * */
    fun delete(movie: Movie)

    /**
     * @return  избранные фильмы
     * может быть пустым
     * */
    fun getAll(): List<Movie>

    /**
     * @return true если фильм в избранному, иначе
     * */
    fun isInFavorites(movie: Movie): Boolean
}

