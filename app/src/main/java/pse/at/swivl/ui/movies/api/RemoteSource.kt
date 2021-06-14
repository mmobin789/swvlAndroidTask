package pse.at.swivl.ui.movies.api

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pse.at.swivl.api.NetworkClient
import pse.at.swivl.ui.movies.domain.models.Photos
import pse.at.swivl.ui.movies.domain.models.PhotosAPIResponse
import retrofit2.HttpException
import java.io.IOException

class RemoteSource(private val apiClient: NetworkClient.API, private val gson: Gson) {
    suspend fun searchMoviePictures(title: String): PhotosAPIResponse =
        withContext(Dispatchers.IO) {
            try {
                val body = apiClient.searchPhotos(title).string()
                val json = body.substringAfter("(").substringBefore("})") + "}"
                val pictures = gson.fromJson(json, Photos::class.java).photos.pictures
                PhotosAPIResponse.Success(pictures)
            } catch (e: IOException) {
                e.printStackTrace()
                PhotosAPIResponse.Failed(e.toString())
            } catch (e: HttpException) {
                e.printStackTrace()
                PhotosAPIResponse.Failed(e.toString())
                // handle api errors
            }
        }
}