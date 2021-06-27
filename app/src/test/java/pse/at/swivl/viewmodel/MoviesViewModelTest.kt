package pse.at.swivl.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pse.at.swivl.ui.movies.MoviesViewModel
import pse.at.swivl.ui.movies.domain.models.MoviesUI
import pse.at.swivl.ui.movies.repository.MoviesRepository

class MoviesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val moviesRepository = mockk<MoviesRepository>()
    private val moviesViewModel = MoviesViewModel(moviesRepository)


    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun testMoviesUILoading() {
        var moviesUI: MoviesUI? = null
        val observer = Observer<MoviesUI> {
            if (moviesUI == null)
                moviesUI = it
        }
        val liveData = moviesViewModel.getMoviesUiData()
        liveData.observeForever(observer)
        coEvery { moviesRepository.loadMovies() } returns mockk()
        moviesViewModel.loadMovies()
        Assert.assertTrue(moviesUI == MoviesUI.Loading)

    }


    @Test
    fun testMoviesUISuccess() {
        val liveData = moviesViewModel.getMoviesUiData()
        val observer = Observer<MoviesUI> {}
        liveData.observeForever(observer)
        coEvery { moviesRepository.loadMovies() } returns mockk()
        moviesViewModel.loadMovies()
        Assert.assertTrue(liveData.value is MoviesUI.Success)

    }

    @Test
    fun testMoviesUISearch() {
        val liveData = moviesViewModel.getMoviesUiData()
        val observer = Observer<MoviesUI> {}
        liveData.observeForever(observer)
        coEvery { moviesRepository.findMoviesByTitle("") } returns mockk()
        moviesViewModel.findMoviesByTitle("")
        Assert.assertTrue(liveData.value is MoviesUI.Success)

    }
}