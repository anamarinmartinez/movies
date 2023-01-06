package com.anamarin.movies.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anamarin.movies.databinding.ActivityDetailBinding
import com.anamarin.movies.model.Movie
import com.anamarin.movies.ui.common.loadUrl

class DetailActivity : AppCompatActivity(), DetailPresenter.View {

    companion object {
        const val MOVIE = "DetailActivity:Movie"
    }

    private val presenter = DetailPresenter()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie: Movie = requireNotNull(intent.getParcelableExtra(MOVIE))
        presenter.onCreate(this, movie)
    }

    override fun updateUi(movie: Movie) = with(binding) {
        movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
        movieDetailSummary.text = movie.overview
        movieDetailToolbar.title = movie.title
        movieDetailInfo.setMovie(movie)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}