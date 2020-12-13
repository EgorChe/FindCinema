package ru.course.findcinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.course.findcinema.feature.top.ui.TopMoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager

        fragmentManager.beginTransaction()
            .add(R.id.container, TopMoviesFragment())
            .commit()
    }
}