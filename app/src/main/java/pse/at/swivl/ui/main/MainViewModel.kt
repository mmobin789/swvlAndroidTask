package pse.at.swivl.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.ui.main.domain.models.Movie
import pse.at.swivl.ui.main.domain.models.MoviePicture

class MainViewModel : ViewModel() {

    fun loadMovies(context: Context, callback: (List<Movie>) -> Unit) {
        viewModelScope.launch {
            MainRepository.loadMovies(context, callback)
        }
    }

    fun searchPhotos(query: String, callback: (List<MoviePicture>) -> Unit) {
        viewModelScope.launch {
            MainRepository.searchPhotos(query)?.also(callback)
        }
    }
}