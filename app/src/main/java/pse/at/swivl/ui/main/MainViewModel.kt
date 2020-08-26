package pse.at.swivl.ui.main

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.base.AppViewModel
import pse.at.swivl.ui.main.domain.models.Movie
import pse.at.swivl.ui.main.domain.models.MoviePicture

class MainViewModel : AppViewModel<MainViewModel.View>() {

    private val moviesData = MutableLiveData<List<Movie>>()
    private val moviePicturesData = MutableLiveData<List<MoviePicture>>()

    private val moviesDataObserver: Observer<in List<Movie>> = Observer {
        getView().onMoviesLoaded(it)
    }

    private val moviePicturesObserver: Observer<in List<MoviePicture>> = Observer {
        getView().onMoviePictureLoaded(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        moviesData.observe(lifecycleOwner, moviesDataObserver)
        moviePicturesData.observe(lifecycleOwner, moviePicturesObserver)
    }

    fun loadMovies() {
        viewModelScope.launch {
            MainRepository.loadMovies {
                moviesData.postValue(it)
            }
        }
    }

    fun findMoviesByTitle(title: String, maxResults: Int) {
        viewModelScope.launch {
            moviesData.postValue(MainRepository.findMoviesByTitle(title, maxResults))
        }
    }

    fun searchPhotos(query: String, callback: (List<MoviePicture>) -> Unit) {
        viewModelScope.launch {
            MainRepository.searchPhotos(query)?.also {
                moviePicturesData.postValue(it)
            }
        }
    }

    interface View {
        fun onMoviesLoaded(movies: List<Movie>)
        fun onMoviePictureLoaded(moviePictures: List<MoviePicture>)
    }
}