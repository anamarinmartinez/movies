package com.anamarin.movies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anamarin.movies.databinding.ActivityMainBinding
import com.anamarin.movies.model.Movie
import com.anamarin.movies.model.MoviesRepository
import com.anamarin.movies.ui.detail.DetailActivity

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val presenter by lazy {
        MainPresenter(MoviesRepository(this), lifecycleScope)
    }

    private lateinit var binding: ActivityMainBinding

    private val adapter = MoviesAdapter { presenter.onMovieClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.onCreate(this@MainActivity)
        binding.recyclerMovies.adapter = adapter
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun updateData(movies: List<Movie>) {
        adapter.submitList(movies)
    }

    override fun navigateTo(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE, movie)
        startActivity(intent)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}