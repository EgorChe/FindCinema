package ru.course.findcinema.feature.top.presentation

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.course.findcinema.Movie

class TopMoviesPresenter : MvpPresenter<TopMoviesView>() {

    private var movies = listOf(
        Movie("Джентельмены", "2020"),
        Movie("Крик", "2002"),
        Movie("Молчание ягнят", "2005"),
        Movie("Властелин Колец", "2001")
    )

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showMovies(movies)
    }

    fun onMovieClick(movie: Movie) {
        viewState.openDetailScreen(movie)
    }
}

interface TopMoviesView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showMovies(movies: List<Movie>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun openDetailScreen(movie: Movie)

}