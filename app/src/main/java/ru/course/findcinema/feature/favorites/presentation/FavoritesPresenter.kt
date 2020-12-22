package ru.course.findcinema.feature.favorites.presentation

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.course.findcinema.data.FavoritesDao
import ru.course.findcinema.domain.Movie

class FavoritesPresenter(
    private val favoritesDao: FavoritesDao
) : MvpPresenter<FavoritesView>() {

    private var movies: List<Movie> = emptyList()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        movies = favoritesDao.getAll()
        viewState.setMovies(movies)
    }
}

interface FavoritesView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setMovies(movies: List<Movie>)

}