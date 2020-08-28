package pse.at.swivl.ui.movies.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pse.at.swivl.api.ApiClientInfo
import pse.at.swivl.ui.movies.domain.models.MoviePicture
import pse.at.swivl.ui.movies.domain.models.Photos
import retrofit2.HttpException
import java.io.IOException

object MovieApiCall : ApiClientInfo {
    suspend fun searchMoviePictures(title: String): List<MoviePicture>? =
        withContext(Dispatchers.IO) {
            var pictures: List<MoviePicture>? = null
            try {
                val body = apiClient.searchPhotos(title).string()
                val json = body.substringAfter("(").substringBefore("})") + "}"
                pictures = gson.fromJson(json, Photos::class.java).photos.pictures
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
                // handle api errors
            }
            pictures
        }
}