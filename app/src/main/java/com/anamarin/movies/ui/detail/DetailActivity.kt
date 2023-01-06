package com.anamarin.movies.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anamarin.movies.databinding.ActivityDetailBinding
import com.anamarin.movies.model.Movie
import com.anamarin.movies.ui.common.loadUrl

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailActivity:Movie"
    }

    private val viewModel: DetailViewModel by viewModels { DetailViewModelFactory(requireNotNull(intent.getParcelableExtra(MOVIE))) }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.state.observe(this) { updateUI(it.movie) }
    }

    private fun updateUI(movie: Movie) = with(binding) {
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        movieDetailSummary.text = movie.overview
        movieDetailToolbar.title = movie.title
        movieDetailInfo.setMovie(movie)
    }
}