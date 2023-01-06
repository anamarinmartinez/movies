package com.anamarin.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.anamarin.movies.R
import com.anamarin.movies.databinding.ActivityDetailBinding
import com.anamarin.movies.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailActivity:Movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.parcelable<Movie>(MOVIE)?.run {
            val background = backdropPath ?: posterPath
            binding.movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780$background")

            binding.movieDetailSummary.text = overview

            binding.movieDetailToolbar.title = title

            binding.movieDetailInfo.text = buildSpannedString {

                bold { append("Original language: ") }
                appendLine(originalLanguage)

                bold { append("Original title: ") }
                appendLine(originalTitle)

                bold { append("Release date: ") }
                appendLine(releaseDate)

                bold { append("Popularity: ") }
                appendLine(popularity.toString())

                bold { append("Vote average: ") }
                appendLine(voteAverage.toString())
            }
        }
    }
}