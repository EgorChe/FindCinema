package ru.course.findcinema.feature.top.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.top_movies_item.*
import ru.course.findcinema.Movie
import ru.course.findcinema.R

class TopMoviesAdapter(
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<TopMoviesAdapter.ViewHolder>() {

    private var movies: MutableList<Movie> = mutableListOf()

    fun setData(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    fun deleteItem(movie: Movie) {
        notifyItemRemoved(movies.indexOf(movie))
        this.movies.remove(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.top_movies_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.containerView.setOnClickListener {
            onMovieClick(item)
        }
        holder.topMoviesItemName.text = item.name
        holder.topMoviesItemYear.text = item.year
    }

    override fun getItemCount(): Int = movies.size


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}