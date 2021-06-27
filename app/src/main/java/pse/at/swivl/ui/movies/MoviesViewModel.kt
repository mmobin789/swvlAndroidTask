package pse.at.swivl.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.ui.movies.domain.models.MoviesUI
import pse.at.swivl.ui.movies.repository.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val moviesUiData = MutableLiveData<MoviesUI>()

    fun getMoviesUiData(): LiveData<MoviesUI> = moviesUiData

    fun loadMovies() {
        viewModelScope.launch {
            moviesUiData.value = MoviesUI.Loading
            moviesUiData.value = MoviesUI.Success(moviesRepository.loadMovies())

        }
    }

    fun findMoviesByTitle(title: String) {
        viewModelScope.launch {
            moviesUiData.value = MoviesUI.Success(
                    moviesRepository.findMoviesByTitle(
                        title
                    )
            )

        }
    }
}