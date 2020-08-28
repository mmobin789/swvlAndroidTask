package pse.at.swivl.ui.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.base.AppViewModel
import pse.at.swivl.ui.movies.MoviesRepository
import pse.at.swivl.ui.movies.domain.models.Movie
import pse.at.swivl.ui.movies.domain.models.MoviePicture

class DetailViewModel : AppViewModel<DetailViewModel.View>() {
    private val moviePicturesData = MutableLiveData<List<MoviePicture>>()

    private val moviePicturesObserver: Observer<in List<MoviePicture>> = Observer {
        getView().onMoviePictureLoaded(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        moviePicturesData.observe(lifecycleOwner, moviePicturesObserver)
    }

    /**
     * The will search for movie's pictures using its title on flickr.com
     * If available pictures would be shown on UI else nothing will be shown.
     * @param title The movie title.
     */
    fun searchMoviePictures(title: String) {
        viewModelScope.launch {
            MoviesRepository.searchMoviePictures(title)?.takeIf { it.isNotEmpty() }?.also {
                moviePicturesData.postValue(it)
            }
        }
    }

    fun findMovieByTitle(title: String, callback: (Movie) -> Unit) {
        viewModelScope.launch {
            MoviesRepository.findMovieByTitle(title)?.also(callback)
        }
    }

    interface View {
        fun onMoviePictureLoaded(moviePictures: List<MoviePicture>)
    }
}