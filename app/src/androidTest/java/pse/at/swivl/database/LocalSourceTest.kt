package pse.at.swivl.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pse.at.swivl.TestApp
import pse.at.swivl.ui.movies.repository.LocalSource
import pse.at.swivl.utils.TestUtils

@RunWith(AndroidJUnit4::class)
class LocalSourceTest {
    private lateinit var localSource: LocalSource


    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<TestApp>()
        val offlineDatabase =
            Room.inMemoryDatabaseBuilder(context, OfflineDatabase::class.java).build()
        val movieDao = offlineDatabase.getMovieDao()
        localSource = LocalSource(movieDao)
    }

    /**
     * The test case for adding movies.
     */
    @Test
    fun addMovies() = runBlocking {
        val ids = localSource.addMovies(
            listOf(
                TestUtils.createMovie(1, "Avengers"),
                TestUtils.createMovie(2, "Avengers:Civil War")
            )
        )
        assertTrue(ids.isNotEmpty())
    }

    /**
     * The test case for all movies available.
     */
    @Test
    fun getAllMovies() = runBlocking {
        val movieCount = 10
        localSource.addMovies(TestUtils.createMovies(movieCount))
        assertTrue(localSource.getMovies().size == movieCount)
    }

    /**
     * The test case for no movies available.
     */
    @Test
    fun getNoMovies() = runBlocking {
        assertFalse(localSource.getMovies().isNotEmpty())
    }

    /* */
    /**
     * The test case for movie available by a title.
     *//*
    @Test
    fun findMovieByTitle() = runBlocking {
        val title = "Avengers:Civil War"
        val movie = TestUtils.createMovie(1, title)
        localSource.addMovies(listOf(movie))
        assertTrue(localSource.findMovieByTitle(title) != null)
    }

    */
    /**
     * The test case for movie not available by a title.
     *//*
    @Test
    fun findNoMovieByTitle() = runBlocking {
        val title = "Test Movie"
        val movie = TestUtils.createMovie(1, "Test Movie 2")
        localSource.addMovies(listOf(movie))
        assertFalse(localSource.findMovieByTitle(title) != null)
    }*/

    /**
     * The test case for maximum search results.
     */
    @Test
    fun findMoviesListByTitle() = runBlocking {
        localSource.addMovies(TestUtils.createMovies(10))
        val foundMovies = localSource.findMoviesByTitle("%movie%", 5, 5)
        assertTrue(foundMovies.size == 5)
    }

    /**
     * The test case for no search results.
     */
    @Test
    fun findEmptyMoviesListByTitle() = runBlocking {
        localSource.addMovies(TestUtils.createMovies(10))
        val foundMovies = localSource.findMoviesByTitle("%test%", 5, 5)
        assertFalse(foundMovies.isNotEmpty())
    }
}