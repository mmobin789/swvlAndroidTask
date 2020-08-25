package pse.at.swivl.ui.main

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pse.at.swivl.ui.base.AppRepository
import pse.at.swivl.ui.main.domain.Movie
import pse.at.swivl.ui.utils.Utils

object MainRepository : AppRepository() {
    private val mGson = Gson()
    private val listType = object : TypeToken<List<Movie>?>() {}.type
    private val movieDao = getOfflineDatabase().getMovieDao()

    suspend fun loadMovies(context: Context, callback: (List<Movie>) -> Unit) {
        var movies = movieDao.getMovies()
        if (movies.isEmpty()) {
            movies = withContext(Dispatchers.IO) {
                mGson.fromJson(Utils.loadJSONStringFromAsset(context)!!, listType)
            }
            movieDao.addMovies(movies)
        }

        callback(movies)

    }
}