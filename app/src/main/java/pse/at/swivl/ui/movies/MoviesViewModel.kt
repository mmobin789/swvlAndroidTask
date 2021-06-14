package pse.at.swivl.ui.movies

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pse.at.swivl.base.AppViewModel
import pse.at.swivl.ui.movies.domain.models.Movie

class MoviesViewModel : AppViewModel<MoviesViewModel.View>() {

    private val moviesData = MutableLiveData<List<Movie>>()
    private val moviesDataObserver: Observer<in List<Movie>> = Observer {
        getView().onMoviesLoaded(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        moviesData.observe(lifecycleOwner, moviesDataObserver)
    }

    fun loadMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviesData.postValue(MoviesRepository.loadMovies())
            }

        }
    }

    fun findMoviesByTitle(title: String, maxResults: Int, rating: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                moviesData.postValue(MoviesRepository.findMoviesByTitle(title, maxResults, rating))
            }
        }
    }

    interface View {
        fun onMoviesLoaded(movies: List<Movie>)
    }
}