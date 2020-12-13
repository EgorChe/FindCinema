package ru.course.findcinema.feature.top.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_top_movies.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.course.findcinema.Movie
import ru.course.findcinema.R
import ru.course.findcinema.feature.search.ui.SearchFragment
import ru.course.findcinema.feature.top.presentation.TopMoviesPresenter
import ru.course.findcinema.feature.top.presentation.TopMoviesView
import ru.course.findcinema.ui.MovieDetailsFragment

class TopMoviesFragment : MvpAppCompatFragment(R.layout.fragment_top_movies), TopMoviesView {

    private var moviesAdapter: TopMoviesAdapter? = null
    private val presenter: TopMoviesPresenter by moxyPresenter {
        TopMoviesPresenter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnGoSearch.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, SearchFragment())
                .addToBackStack("SearchFragment")
                .commit()
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

    override fun openDetailScreen(movie: Movie) {
        requireFragmentManager().beginTransaction()
            .replace(R.id.container, MovieDetailsFragment.newInstance(movie))
            .addToBackStack("MovieDetailsFragmeДелоnt")
            .commit()
    }
}

