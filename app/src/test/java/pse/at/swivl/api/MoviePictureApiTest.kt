package pse.at.swivl.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import pse.at.swivl.ui.movies.api.MovieApiCall

/**
 * This class implementation paves the way to test api calls synchronously for everything.
 * This api call can be executed on the local machine.
 * For demo purposes, the test code is pointing to app's production base url it should point to the app's staging base url.
 * What to expect,
 * In case of success, you get data from API and test passes.
 * In any other case, internet error or bad request or time out test fails.
 */
class MoviePictureApiTest {
    /**
     * A test case where flickr api returns pictures for the movie title The Strange Ones.
     */
    @Test
    fun findMoviePictureListByTitle() = runBlocking {
        val picturesList = MovieApiCall.searchMoviePictures("The Strange Ones")
        Assert.assertFalse(picturesList.isNullOrEmpty())
    }

    /**
     * A test case where flickr api doesn't returns pictures for the movie title Insidious: The Last Key.
     */
    @Test
    fun findEmptyMoviePicturesListByTitle() = runBlocking {
        val picturesList = MovieApiCall.searchMoviePictures("Insidious: The Last Key")
        Assert.assertTrue(picturesList.isNullOrEmpty())
    }
}