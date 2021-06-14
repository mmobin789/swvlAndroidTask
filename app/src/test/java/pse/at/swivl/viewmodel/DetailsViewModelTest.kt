package pse.at.swivl.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pse.at.swivl.ui.detail.DetailViewModel
import pse.at.swivl.ui.movies.domain.models.MoviePicturesUI
import pse.at.swivl.ui.movies.domain.models.PhotosAPIResponse
import pse.at.swivl.ui.movies.repository.MoviesRepository

class DetailsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val moviesRepository = mockk<MoviesRepository>()
    private val detailViewModel = DetailViewModel(moviesRepository)


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun testSearchMoviePictureUILoading() {
        val observer = Observer<MoviePicturesUI> {
        }
        val liveData = detailViewModel.getMoviePicturesData()
        liveData.observeForever(observer)
        coEvery { moviesRepository.searchMoviePictures("") } returns mockk()
        detailViewModel.searchMoviePictures("")
        Assert.assertTrue(liveData.value == MoviePicturesUI.Loading)
    }

    @Test
    fun testSearchMoviePictureUISuccess() {
        val observer = Observer<MoviePicturesUI> {
        }
        val liveData = detailViewModel.getMoviePicturesData()
        liveData.observeForever(observer)
        val photosAPIResponse = mockk<PhotosAPIResponse.Success>()
        every { photosAPIResponse.moviePictures } returns mockk()
        val moviePictures = photosAPIResponse.moviePictures
        every { moviePictures.isEmpty() } returns false
        coEvery { moviesRepository.searchMoviePictures("") } returns photosAPIResponse
        detailViewModel.searchMoviePictures("")
        Assert.assertTrue(liveData.value is MoviePicturesUI.Success)
    }

    @Test
    fun testSearchMoviePictureUIFailed() {
        val observer = Observer<MoviePicturesUI> {
        }
        val liveData = detailViewModel.getMoviePicturesData()
        liveData.observeForever(observer)
        val photosAPIResponse = mockk<PhotosAPIResponse.Success>()
        every { photosAPIResponse.moviePictures } returns mockk()
        val moviePictures = photosAPIResponse.moviePictures
        every { moviePictures.isEmpty() } returns false
        coEvery { moviesRepository.searchMoviePictures("") } returns photosAPIResponse
        detailViewModel.searchMoviePictures("")
        Assert.assertTrue(liveData.value is MoviePicturesUI.Success)
    }
}