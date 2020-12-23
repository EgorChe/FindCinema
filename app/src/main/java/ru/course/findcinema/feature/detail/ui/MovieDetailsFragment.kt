package ru.course.findcinema.feature.detail.ui

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.R
import ru.course.findcinema.domain.Movie
import ru.course.findcinema.feature.detail.presentation.MovieDetailPresenter
import ru.course.findcinema.feature.detail.presentation.MovieDetailPresenterFactory
import ru.course.findcinema.feature.detail.presentation.MovieDetailView
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : MvpAppCompatFragment(R.layout.fragment_movie_details),
    MovieDetailView {

    companion object {

        private const val MOVIE = "MOVIE"

        fun newInstance(movie: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE, movie)
                }
            }
    }

    @Inject
    lateinit var movieDetailPresenterFactory: MovieDetailPresenterFactory
    private val presenter: MovieDetailPresenter by moxyPresenter {
        movieDetailPresenterFactory.create(arguments?.getParcelable(MOVIE)!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailFavorite.setOnClickListener {
            presenter.onFavoritesClicked()
        }
    }

    override fun setMovie(movie: Movie) {
        tvName.text = "Фильм: ${movie.name}"
        tvYearCreation.text = "Год: ${movie.year}"
    }

    override fun setIsInFavorites(inFavorites: Boolean) {
        movieDetailFavorite.setImageResource(
            if (inFavorites) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
        )
    }
}

