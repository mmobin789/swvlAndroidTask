package pse.at.swivl.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pse.at.swivl.ui.movies.api.MovieApiCall

class MoviePictureApiTest {
    /**
     * This implementation paves the way to test api calls synchronously for everything.
     * This api call can be executed on the local machine.
     * For demo purposes, the test code is pointing to app's production base url it should point to the app's staging base url.
     * What to expect,
     * In case of success, you get data from API and test passes.
     * In any other case, internet error or bad request or time out test fails.
     */
    @Test
    fun findMoviePhotosByTitle() = runBlocking {
        val picturesList = MovieApiCall.searchMoviePictures("The Strange Ones")
        Assert.assertTrue(!picturesList.isNullOrEmpty())
    }
}