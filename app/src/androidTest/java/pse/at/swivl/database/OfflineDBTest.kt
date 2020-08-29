package pse.at.swivl.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pse.at.swivl.TestApp
import pse.at.swivl.ui.movies.domain.dao.MovieDao
import pse.at.swivl.utils.TestUtils
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class OfflineDBTest {

    private lateinit var movieDao: MovieDao
    private lateinit var offlineDatabase: OfflineDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<TestApp>()
        offlineDatabase = Room.inMemoryDatabaseBuilder(context, OfflineDatabase::class.java).build()
        movieDao = offlineDatabase.getMovieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() = offlineDatabase.close()

    /**
     * The test case for adding movies.
     */
    @Test
    fun addMovies() = runBlocking {
        val ids = movieDao.addMovies(
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
        movieDao.addMovies(TestUtils.createMovies(movieCount))
        assertTrue(movieDao.getMovies().size == movieCount)
    }

    /**
     * The test case for no movies available.
     */
    @Test
    fun getNoMovies() = runBlocking {
        assertFalse(movieDao.getMovies().isNotEmpty())
    }

    /**
     * The test case for movie available by a title.
     */
    @Test
    fun findMovieByTitle() = runBlocking {
        val title = "Avengers:Civil War"
        val movie = TestUtils.createMovie(1, title)
        movieDao.addMovies(listOf(movie))
        assertTrue(movieDao.findMovieByTitle(title) != null)
    }

    /**
     * The test case for movie not available by a title.
     */
    @Test
    fun findNoMovieByTitle() = runBlocking {
        val title = "Test Movie"
        val movie = TestUtils.createMovie(1, "Test Movie 2")
        movieDao.addMovies(listOf(movie))
        assertFalse(movieDao.findMovieByTitle(title) != null)
    }

    /**
     * The test case for maximum search results.
     */
    @Test
    fun findMoviesListByTitle() = runBlocking {
        movieDao.addMovies(TestUtils.createMovies(10))
        val foundMovies = movieDao.findMoviesByTitle("%movie%", 5, 5)
        assertTrue(foundMovies.size == 5)
    }

    /**
     * The test case for no search results.
     */
    @Test
    fun findEmptyMoviesListByTitle() = runBlocking {
        movieDao.addMovies(TestUtils.createMovies(10))
        val foundMovies = movieDao.findMoviesByTitle("%test%", 5, 5)
        assertFalse(foundMovies.isNotEmpty())
    }
}