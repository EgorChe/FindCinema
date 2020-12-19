package ru.course.findcinema.feature.detail.presentation

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.course.findcinema.Movie
import ru.course.findcinema.data.FavoritesDao

class MovieDetailPresenter(
    private val movie: Movie,
    private val favoritesDao: FavoritesDao
) : MvpPresenter<MovieDetailView>() {

    private var isInFavorites: Boolean = false

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setMovie(movie)
        isInFavorites = favoritesDao.isInFavorites(movie)
        viewState.setIsInFavorites(isInFavorites)
    }

    fun onFavoritesClick() {
        if (isInFavorites) {
            favoritesDao.delete(movie)
        } else {
            favoritesDao.add(movie)
        }
        isInFavorites = !isInFavorites
        viewState.setIsInFavorites(isInFavorites)
    }
}

interface MovieDetailView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setMovie(movie: Movie)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setIsInFavorites(isInFavorites: Boolean)
}
