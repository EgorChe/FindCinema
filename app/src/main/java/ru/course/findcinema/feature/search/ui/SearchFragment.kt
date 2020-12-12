package ru.course.findcinema.feature.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_search.*
import ru.course.findcinema.R
import ru.course.findcinema.feature.search.presentation.SearchPresenter
import ru.course.findcinema.feature.search.presentation.SearchView

enum class Type { ALL, FILM, TV_SHOWS }

class SearchFragment : Fragment(R.layout.fragment_search), SearchView {


    private val presenter = SearchPresenter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        initListeners()
    }

    private fun initListeners() {

        rgType.setOnCheckedChangeListener { radioGroup, i ->
            val selectedType = when (i) {
                R.id.rbFilm -> Type.FILM
                R.id.rbTvShow -> Type.TV_SHOWS
                else -> Type.ALL
            }

            presenter.setType(selectedType)
        }

        btnSearch.setOnClickListener {
            presenter.validate(
                etRatingFrom.text.toString(),
                etRatingTo.text.toString(),
                etYearFrom.text.toString(),
                etYearTo.text.toString()
            )
        }
    }

    override fun showRatingFromError() {
        showErrorToast("Рейтинг от")
    }

    override fun showRatingToError() {
        showErrorToast("Рейтинг до")
    }

    override fun showYearFromError() {
        showErrorToast("Год от")
    }

    override fun showYearToError() {
        showErrorToast("Год до")
    }

    private fun showErrorToast(name: String) {
        Toast.makeText(requireContext(), "Ошибка в поле: $name", Toast.LENGTH_LONG).show()
    }

}