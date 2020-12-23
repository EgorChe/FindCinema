package ru.course.findcinema.feature.top.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_top_movies.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.R
import ru.course.findcinema.domain.Movie
import ru.course.findcinema.feature.detail.ui.MovieDetailsFragment
import ru.course.findcinema.feature.favorites.ui.FavoritesFragment
import ru.course.findcinema.feature.search.ui.SearchFragment
import ru.course.findcinema.feature.top.presentation.TopMoviesPresenter
import ru.course.findcinema.feature.top.presentation.TopMoviesView
import javax.inject.Inject

@AndroidEntryPoint
class TopMoviesFragment : MvpAppCompatFragment(R.layout.fragment_top_movies), TopMoviesView {

    @Inject
    lateinit var topMoviesPresenter: TopMoviesPresenter
    private var moviesAdapter: TopMoviesAdapter? = null
    private val presenter: TopMoviesPresenter by moxyPresenter { topMoviesPresenter }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoSearch.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, SearchFragment())
                .addToBackStack("SearchFragment")
                .commit()
        }
        btnGoFavorites.setOnClickListener {
            presenter.onFavoritesClick()
        }
        with(rvTopMovies) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = TopMoviesAdapter(onMovieClick = { presenter.onMovieClick(it) }).also {
                moviesAdapter = it
            }
        }
    }

    override fun onDestroyView() {
        moviesAdapter = null
        super.onDestroyView()
    }

    override fun showMovies(movies: List<Movie>) {
        moviesAdapter?.submitList(movies)
    }

    override fun showLoading(isShow: Boolean) {
        topMoviesProgress.isVisible = isShow
    }

    override fun openDetailScreen(movie: Movie) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack("MovieDetailsFragmeДелоnt")
            .commit()
    }

    override fun openFavoritesScreen() {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, FavoritesFragment())
            .addToBackStack("FavoritesFragment")
            .commit()
    }
}

