package ru.course.findcinema.feature.favorites.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.Movie
import ru.course.findcinema.R
import ru.course.findcinema.data.FavoritesDaoImpl
import ru.course.findcinema.feature.favorites.presentetion.FavoritesPresenter
import ru.course.findcinema.feature.favorites.presentetion.FavoritesView
import ru.course.findcinema.feature.top.ui.TopMoviesAdapter

class FavoritesFragment : MvpAppCompatFragment(R.layout.fragment_favorites), FavoritesView {

    companion object {

        fun newInstance() = FavoritesFragment()
    }

    private val presenter: FavoritesPresenter by moxyPresenter {
        FavoritesPresenter(
            favoritesDao = FavoritesDaoImpl(
                requireContext().getSharedPreferences(
                    "data",
                    Context.MODE_PRIVATE
                )
            )
        )
    }

    private var favoritesAdapter: TopMoviesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(favoritesList) {
            layoutManager = LinearLayoutManager(context)
            favoritesAdapter = TopMoviesAdapter(onMovieClick = {})
            adapter = favoritesAdapter
        }
    }

    override fun setMovies(movies: List<Movie>) {
        favoritesAdapter?.submitList(movies)
    }
}