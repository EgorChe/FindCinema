package ru.course.findcinema.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_movie_details.*
import ru.course.findcinema.Movie
import ru.course.findcinema.R

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    companion object {

        private const val MOVIE = "MOVIE"

        fun newInstance(movie: Movie) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(MOVIE, movie)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val movie = it.getParcelable<Movie>(MOVIE)

            tvName.text = "Фильм: ${movie?.name}"
            tvYearCreation.text = "Год: ${movie?.year}"
        }
    }

}