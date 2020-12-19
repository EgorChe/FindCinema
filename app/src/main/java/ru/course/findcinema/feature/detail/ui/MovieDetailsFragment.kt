package ru.course.findcinema.feature.detail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_movie_details.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.Movie
import ru.course.findcinema.R
import ru.course.findcinema.data.FavoritesDaoImpl
import ru.course.findcinema.feature.detail.presentation.MovieDetailPresenter
import ru.course.findcinema.feature.detail.presentation.MovieDetailView

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

    private val presenter: MovieDetailPresenter by moxyPresenter {
        MovieDetailPresenter(
            movie = arguments?.getParcelable(MOVIE)!!,
            favoritesDao = FavoritesDaoImpl(
                requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailFavorites.setOnClickListener {
            presenter.onFavoritesClick()
        }
    }

    override fun setMovie(movie: Movie) {
        tvName.text = "Фильм: ${movie?.name}"
        tvYearCreation.text = "Год: ${movie?.year}"
    }

    override fun setIsInFavorites(isInFavorites: Boolean) {
        movieDetailFavorites.setImageResource(if (isInFavorites) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
    }
}