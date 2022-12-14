package com.anamarin.movies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.anamarin.movies.model.Movie
import com.anamarin.movies.model.MoviesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState>
        get() {
            if (_state.value?.movies == null) {
                refresh()
            }

            return _state
        }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(true)
            _state.value = UiState(movies = moviesRepository.findPopularMovies().results)
        }
    }

    fun onMovieClicked(movie: Movie) {
        _state.value = _state.value?.copy(navigateTo = movie)
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie>? = null,
        val navigateTo: Movie? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(moviesRepository) as T
    }
}