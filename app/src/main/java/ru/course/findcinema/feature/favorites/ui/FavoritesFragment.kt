package ru.course.findcinema.feature.favorites.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.favorites_fragment.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.R
import ru.course.findcinema.data.FavoritesDaoImpl
import ru.course.findcinema.domain.Movie
import ru.course.findcinema.feature.favorites.presentation.FavoritesPresenter
import ru.course.findcinema.feature.favorites.presentation.FavoritesView
import ru.course.findcinema.feature.top.ui.TopMoviesAdapter

class FavoritesFragment : MvpAppCompatFragment(R.layout.favorites_fragment), FavoritesView {

    companion object {

        fun newInstance() = FavoritesFragment()
    }

    private val presenter: FavoritesPresenter by moxyPresenter {
        FavoritesPresenter(
            favoritesDao = FavoritesDaoImpl(
                requireContext().getSharedPreferences("data", Context.MODE_PRIVATE)
            )
        )
    }

    private var favoritesAdapter: TopMoviesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(favoritesList) {
            favoritesAdapter = TopMoviesAdapter(onMovieClick = {
            })
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
        }
    }

    override fun setMovies(movies: List<Movie>) {
        favoritesAdapter?.submitList(movies)
    }
}