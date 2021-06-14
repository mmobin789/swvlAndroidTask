package pse.at.swivl.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pse.at.swivl.ui.movies.domain.models.MoviePicturesUI
import pse.at.swivl.ui.movies.domain.models.PhotosAPIResponse
import pse.at.swivl.ui.movies.repository.MoviesRepository

class DetailViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private val moviePicturesData = MutableLiveData<MoviePicturesUI>()

    fun getMoviePicturesData() = moviePicturesData as LiveData<MoviePicturesUI>


    /**
     * The will search for movie's pictures using its title on flickr.com
     * If available, pictures would be shown on UI else nothing will be shown.
     * @param title The movie title.
     */
    fun searchMoviePictures(title: String) {
        viewModelScope.launch {
            moviePicturesData.postValue(MoviePicturesUI.Loading)
            when (val response = moviesRepository.searchMoviePictures(title)) {
                is PhotosAPIResponse.Success -> {
                    val pictures = response.moviePictures
                    if(pictures.isEmpty())
                        moviePicturesData.postValue(MoviePicturesUI.Failed("Movie Pictures not available."))
                    else
                    moviePicturesData.postValue(MoviePicturesUI.Success(response.moviePictures))
                }
                is PhotosAPIResponse.Failed -> {
                    moviePicturesData.postValue(MoviePicturesUI.Failed(response.error))
                }
            }
        }
    }

    /* fun findMovieById(id: Int, callback: (Movie) -> Unit) {
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 MoviesRepository.findMovieById(id)
             }?.also(callback)

         }
     }*/
}