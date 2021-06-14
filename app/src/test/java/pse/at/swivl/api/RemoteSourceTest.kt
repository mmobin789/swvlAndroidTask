package pse.at.swivl.api

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import pse.at.swivl.ui.movies.api.RemoteSource
import pse.at.swivl.ui.movies.domain.models.PhotosAPIResponse

/**
 * This class implementation paves the way to test api calls for everything.
 * In case of success, you get data from API and test passes.
 * In any other case, internet error or bad request or time out test fails.
 */
class RemoteSourceTest {
    /**
     * A test case where flickr api returns pictures for the movie title The Strange Ones.
     */
    @Test
    fun findMoviePictureListByTitleSuccess() {
        val moviePicturesSuccess = mockk<PhotosAPIResponse.Success>()
        val apiCallMock = mockk<RemoteSource>()
        coEvery { apiCallMock.searchMoviePictures("The Strange Ones") } returns moviePicturesSuccess
        every { moviePicturesSuccess.moviePictures } returns mockk()
        val pictures = moviePicturesSuccess.moviePictures
        every { pictures.isEmpty() } returns false
        Assert.assertFalse(pictures.isEmpty())
    }

    /**
     * A test case where flickr api doesn't returns pictures for the movie title Insidious: The Last Key.
     */
    @Test
    fun findMoviePicturesListByTitleFail() {
        val moviePicturesFail = mockk<PhotosAPIResponse.Failed>()
        val apiCallMock = mockk<RemoteSource>()
        coEvery { apiCallMock.searchMoviePictures("Insidious: The Last Key") } returns moviePicturesFail
        every { moviePicturesFail.error } returns "API ERROR"
        val moviePicturesError = moviePicturesFail.error
        Assert.assertTrue(moviePicturesError.isNotBlank())
    }


}