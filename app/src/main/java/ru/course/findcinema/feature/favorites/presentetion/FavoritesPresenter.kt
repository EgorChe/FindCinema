package ru.course.findcinema.feature.favorites.presentetion

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.course.findcinema.Movie
import ru.course.findcinema.data.FavoritesDao

class FavoritesPresenter(private val favoritesDao: FavoritesDao) : MvpPresenter<FavoritesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setMovies(favoritesDao.getAll())
    }
}

interface FavoritesView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setMovies(movies: List<Movie>)

}