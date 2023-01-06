package com.anamarin.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anamarin.movies.databinding.ActivityDetailBinding
import com.anamarin.movies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailActivity:Movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityDetailBinding.inflate(layoutInflater).run {
            setContentView(root)

            val movie = intent.parcelable<Movie>(MOVIE) ?: throw IllegalStateException()
            val background = movie.backdropPath ?: movie.posterPath

            movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$background")

            movieDetailSummary.text = movie.overview

            movieDetailToolbar.title = movie.title

            movieDetailInfo.setMovie(movie)
        }
    }
}