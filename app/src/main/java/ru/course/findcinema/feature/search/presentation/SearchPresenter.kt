package ru.course.findcinema.feature.search.presentation

import moxy.MvpPresenter
import ru.course.findcinema.feature.search.ui.Type
import java.lang.Exception


class SearchPresenter : MvpPresenter<SearchView>() {

    private var selectedType: Type = Type.ALL

    fun setType(type: Type) {
        selectedType = type
    }

    fun validate(ratingFrom: String, ratingTo: String, yearFrom: String, yearTo: String) {
        when {
            !ratingIsCorrect(ratingFrom) -> viewState.showRatingFromError()
            !ratingIsCorrect(ratingTo) -> viewState.showRatingToError()
            !yearIsCorrect(yearFrom) -> viewState.showYearFromError()
            !yearIsCorrect(yearTo) -> viewState.showYearToError()
        }
    }

    private fun ratingIsCorrect(ratingText: String): Boolean {
        if (ratingText.isEmpty()) return false

        return try {
            val rating = ratingText.toInt()
            rating in 0..10
        } catch (e: Exception) {
            false
        }
    }

    private fun yearIsCorrect(yearText: String): Boolean {
        if (yearText.isEmpty()) return false

        return try {
            val rating = yearText.toInt()
            rating in 1950..2020
        } catch (e: Exception) {
            false
        }
    }
}