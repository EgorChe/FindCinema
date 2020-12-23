package ru.course.findcinema.feature.top.presentation

import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenterScope
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.course.findcinema.domain.GetTopMoviesUseCase
import ru.course.findcinema.domain.Movie
import ru.course.findcinema.extensions.launchWithErrorHandler
import javax.inject.Inject

class TopMoviesPresenter @Inject constructor(
    private val getTopMoviesUseCase: GetTopMoviesUseCase
) : MvpPresenter<TopMoviesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLoading(isShow = true)
        presenterScope.launchWithErrorHandler(block = {
            val movies = getTopMoviesUseCase()
            viewState.showMovies(movies)
            viewState.showLoading(isShow = false)
        }, onError = {
            viewState.showLoading(isShow = false)
        })
    }

    fun onMovieClick(movie: Movie) {
        viewState.openDetailScreen(movie)
    }

    fun onFavoritesClick() {
        viewState.openFavoritesScreen()
    }
}

interface TopMoviesView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMovies(movies: List<Movie>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetailScreen(movie: Movie)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openFavoritesScreen()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showLoading(isShow: Boolean)
}