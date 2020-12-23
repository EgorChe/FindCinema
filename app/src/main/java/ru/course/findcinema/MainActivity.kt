package ru.course.findcinema

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.course.findcinema.feature.top.ui.TopMoviesFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragmentManager = supportFragmentManager

            if (fragmentManager.fragments.size == 0) {
                fragmentManager.beginTransaction()
                    .add(R.id.container, TopMoviesFragment())
                    .commit()
            }
        }

    }
}