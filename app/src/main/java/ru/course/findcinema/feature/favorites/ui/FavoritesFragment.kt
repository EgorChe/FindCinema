package ru.course.findcinema.feature.favorites.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.favorites_fragment.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.R
import ru.course.findcinema.domain.Movie
import ru.course.findcinema.feature.favorites.presentation.FavoritesPresenter
import ru.course.findcinema.feature.favorites.presentation.FavoritesView
import ru.course.findcinema.feature.top.ui.TopMoviesAdapter
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : MvpAppCompatFragment(R.layout.favorites_fragment), FavoritesView {

    companion object {

        fun newInstance() = FavoritesFragment()
    }

    @Inject
    lateinit var favoritesPresenter: FavoritesPresenter

    private val presenter: FavoritesPresenter by moxyPresenter { favoritesPresenter }

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