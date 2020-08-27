package pse.at.swivl.ui.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.base.AppViewModel
import pse.at.swivl.ui.main.MainRepository
import pse.at.swivl.ui.main.domain.models.Movie
import pse.at.swivl.ui.main.domain.models.MoviePicture

class DetailViewModel : AppViewModel<DetailViewModel.View>() {
    private val moviePicturesData = MutableLiveData<List<MoviePicture>>()

    private val moviePicturesObserver: Observer<in List<MoviePicture>> = Observer {
        getView().onMoviePictureLoaded(it)
    }

    fun addObservers(lifecycleOwner: LifecycleOwner) {
        moviePicturesData.observe(lifecycleOwner, moviePicturesObserver)
    }

    fun searchPhotos(query: String) {
        viewModelScope.launch {
            MainRepository.searchPhotos(query)?.also {
                moviePicturesData.postValue(it)
            }
        }
    }

    fun findMovieByTitle(title: String, callback: (Movie) -> Unit) {
        viewModelScope.launch {
            MainRepository.findMovieByTitle(title)?.also(callback)
        }
    }

    interface View {
        fun onMoviePictureLoaded(moviePictures: List<MoviePicture>)
    }
}