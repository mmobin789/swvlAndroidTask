package pse.at.swivl.ui.movies

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pse.at.swivl.ui.base.AppRepository
import pse.at.swivl.ui.movies.api.MovieApiCall
import pse.at.swivl.ui.movies.domain.models.Movie
import pse.at.swivl.ui.utils.Utils

/**
 * The movies repository providing online/offline database access to movie and its specific data.
 */
object MoviesRepository : AppRepository() {
    private val mGson = Gson()
    private val listType = object : TypeToken<List<Movie>?>() {}.type
    private val movieDao = getOfflineDatabase().getMovieDao()

    suspend fun findMoviesByTitle(title: String, maxResults: Int, rating: Int) =
        movieDao.findMoviesByTitle("%$title%", maxResults, rating)

    /**
     * The movies are cached on 1st app launch from movies.json file and then loaded in sorted order from database.
     */
    suspend fun loadMovies(callback: (List<Movie>) -> Unit) {
        var moviesList = movieDao.getMovies()
        if (moviesList.isEmpty()) {
            moviesList = withContext(Dispatchers.IO) {
                mGson.fromJson(Utils.loadJSONStringFromAsset(context)!!, listType)
            }
            movieDao.addMovies(moviesList)

            moviesList = movieDao.getMovies()
        }

        callback(moviesList)

    }

    suspend fun findMovieByTitle(title: String) = movieDao.findMovieByTitle(title)


    suspend fun searchMoviePictures(title: String) = MovieApiCall.searchMoviePictures(title)

}