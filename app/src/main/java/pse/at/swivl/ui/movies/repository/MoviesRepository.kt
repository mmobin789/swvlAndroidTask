package pse.at.swivl.ui.movies.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pse.at.swivl.ui.movies.api.RemoteSource
import pse.at.swivl.ui.movies.domain.models.Movie
import pse.at.swivl.ui.utils.Utils

/**
 * The movies repository providing online/offline database access to movie and its specific data.
 */
class MoviesRepository(
    private val context: Context,
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource,
    private val gson: Gson
) {

    private val listType = object : TypeToken<List<Movie>?>() {}.type


    /**
     * Each search query will trigger IO bound threads to search in database.
     * This will scramble the UX so this method which runs the query is synchronized so only single thread access is allowed.
     */
    @Synchronized
    suspend fun findMoviesByTitle(title: String, maxResults: Int, rating: Int) =
        withContext(Dispatchers.IO) {
            localSource.findMoviesByTitle(title, maxResults, rating)
        }

    /**
     * The movies are cached on 1st app launch from movies.json file and then loaded in sorted order from database.
     */
    @Synchronized
    suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        var moviesList = localSource.getMovies()
        if (moviesList.isEmpty()) {
            moviesList = gson.fromJson(Utils.loadJSONStringFromAsset(context)!!, listType)
            localSource.addMovies(moviesList)
            moviesList = localSource.getMovies()
        }
        moviesList
    }

// fun findMovieByTitle(title: String) = movieDao.findMovieByTitle(title)

//fun findMovieById(id: Int) = movieDao.findMovieById(id)


    suspend fun searchMoviePictures(title: String) = remoteSource.searchMoviePictures(title)

}