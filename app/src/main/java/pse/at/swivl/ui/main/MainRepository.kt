package pse.at.swivl.ui.main

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import pse.at.swivl.api.NetworkClient
import pse.at.swivl.ui.base.AppRepository
import pse.at.swivl.ui.main.domain.models.Movie
import pse.at.swivl.ui.main.domain.models.MoviePicture
import pse.at.swivl.ui.main.domain.models.Photos
import pse.at.swivl.ui.utils.Utils

object MainRepository : AppRepository() {
    private val mGson = Gson()
    private val listType = object : TypeToken<List<Movie>?>() {}.type
    private val movieDao = getOfflineDatabase().getMovieDao()

    suspend fun findMoviesByTitle(title: String, maxResults: Int, rating: Int) =
        movieDao.findMoviesByTitle("%$title%", maxResults, rating)


    suspend fun loadMovies(callback: (List<Movie>) -> Unit) {
        var movies = movieDao.getMovies()
        if (movies.isEmpty()) {
            movies = withContext(Dispatchers.IO) {
                mGson.fromJson(Utils.loadJSONStringFromAsset(context)!!, listType)
            }
            movieDao.addMovies(movies)
        }

        callback(movies)

    }

    suspend fun searchPhotos(query: String): List<MoviePicture>? =
        withContext(Dispatchers.IO) {
            var pictures: List<MoviePicture>? = null
            try {
                val body = NetworkClient.getApiClient().searchPhotos(query).string()
                val json = body.substringAfter("(").substringBefore("})") + "}"
                pictures = mGson.fromJson(json, Photos::class.java).photos.pictures
            } catch (e: IOException) {
                e.printStackTrace()
            }
            pictures
        }

}