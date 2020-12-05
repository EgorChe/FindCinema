package ru.course.findcinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cinema_details.*

class CinemaDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cinema_details)

        val cinema = intent.extras?.getSerializable("Cinema") as Cinema

        tvName.text = "Фильм: ${cinema.name}"
        tvYearCreation.text = "Год: ${cinema.year}"
    }
}