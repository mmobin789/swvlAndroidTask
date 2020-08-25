package pse.at.swivl.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.ui.main.domain.Movie

class MainViewModel : ViewModel() {

    fun loadMovies(context: Context, callback: (List<Movie>) -> Unit) = viewModelScope.launch {
        MainRepository.loadMovies(context, callback)
    }
}