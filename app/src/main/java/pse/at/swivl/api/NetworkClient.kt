package pse.at.swivl.api

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object NetworkClient {
    private const val baseURL = "https://api.flickr.com/services/rest/"
    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    interface API {
        @GET("?method=flickr.photos.search&api_key=8ce9b1555c83aa5e7a309c5376edae4c&format=json&per_page=10")
        suspend fun searchPhotos(@Query("text") s: String): ResponseBody
    }
}