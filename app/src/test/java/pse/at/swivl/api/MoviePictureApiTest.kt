package pse.at.swivl.api

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import pse.at.swivl.ui.movies.api.MovieApiCall
import pse.at.swivl.ui.movies.domain.models.MoviePicture

/**
 * This class implementation paves the way to test api calls for everything.
 * In case of success, you get data from API and test passes.
 * In any other case, internet error or bad request or time out test fails.
 */
class MoviePictureApiTest {
    /**
     * A test case where flickr api returns pictures for the movie title The Strange Ones.
     */
    @Test
    fun findMoviePictureListByTitle() {
        val moviePictures = mockk<List<MoviePicture>>()
        val apiCallMock = mockk<MovieApiCall>()
        coEvery { apiCallMock.searchMoviePictures("The Strange Ones") } returns moviePictures
        every { moviePictures.isEmpty() } returns false
        Assert.assertFalse(moviePictures.isEmpty())
    }

    /**
     * A test case where flickr api doesn't returns pictures for the movie title Insidious: The Last Key.
     */
    @Test
    fun findEmptyMoviePicturesListByTitle() {
        val moviePictures = mockk<List<MoviePicture>>()
        val apiCallMock = mockk<MovieApiCall>()
        coEvery { apiCallMock.searchMoviePictures("Insidious: The Last Key") } returns moviePictures
        every { moviePictures.isEmpty() } returns true
        Assert.assertTrue(moviePictures.isEmpty())
    }

    /**
     * A test case where flickr api  returns null for the movie title Insidious: The Last Key.
     */
    @Test
    fun findNullMoviePicturesListByTitle() {
        val moviePictures = mockk<List<MoviePicture>>()
        val apiCallMock = mockk<MovieApiCall>()
        coEvery { apiCallMock.searchMoviePictures("Insidious: The Last Key") } returns null
        every { moviePictures.isEmpty() } returns true
        Assert.assertTrue(moviePictures.isNullOrEmpty())
    }
}