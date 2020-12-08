package ru.course.findcinema.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_top_movies.*
import ru.course.findcinema.MainActivity
import ru.course.findcinema.Movie
import ru.course.findcinema.R

class TopMoviesFragment : Fragment(R.layout.fragment_top_movies) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = Movie("Джентельмены", "2020")

        btnGoToDetails.setOnClickListener {
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, MovieDetailsFragment.newInstance(movie))
                .addToBackStack("MovieDetailsFragment")
                .commit()
        }

        (activity as MainActivity).helloFragment()
    }
}