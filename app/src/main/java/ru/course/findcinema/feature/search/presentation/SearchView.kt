package ru.course.findcinema.feature.search.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

interface SearchView : MvpView {

    @Skip
    fun showRatingFromError()

    @Skip
    fun showRatingToError()

    @Skip
    fun showYearFromError()

    @Skip
    fun showYearToError()
}