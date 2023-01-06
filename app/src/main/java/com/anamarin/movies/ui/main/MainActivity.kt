package com.anamarin.movies.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anamarin.movies.databinding.ActivityMainBinding
import com.anamarin.movies.model.MoviesRepository
import com.anamarin.movies.ui.detail.DetailActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val moviesRepository by lazy { MoviesRepository(this) }

    private val adapter = MoviesAdapter {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.MOVIE, it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(ActivityMainBinding.inflate(layoutInflater)) {
            setContentView(root)
            recyclerMovies.adapter = adapter

            lifecycleScope.launch {
                progress.visibility = View.VISIBLE
                adapter.submitList(moviesRepository.findPopularMovies().results)
                progress.visibility = View.GONE
            }
        }
    }
}