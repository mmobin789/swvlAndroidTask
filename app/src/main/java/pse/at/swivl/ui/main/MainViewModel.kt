package pse.at.swivl.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.ui.main.domain.Movie
import pse.at.swivl.ui.main.domain.MoviePicture

class MainViewModel : ViewModel() {

    fun loadMovies(context: Context, callback: (List<Movie>) -> Unit) = viewModelScope.launch {
        MainRepository.loadMovies(context, callback)
    }

    fun searchPhotos(query: String, callback: (MoviePicture) -> Unit) = viewModelScope.launch {
        callback(MainRepository.searchPhotos(query))
    }
}