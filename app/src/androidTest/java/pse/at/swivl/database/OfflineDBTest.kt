package pse.at.swivl.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
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


    @Test
    fun addMovies() = runBlocking {
        val ids = movieDao.addMovies(
            listOf(
                TestUtils.createMovie(1, "Avengers"),
                TestUtils.createMovie(2, "Avengers:Civil War")
            )
        )
        assertEquals(true, ids.isNotEmpty())
    }

    @Test
    fun getAllMovies() = runBlocking {
        val movieCount = 10
        movieDao.addMovies(TestUtils.createMovies(movieCount))
        assertEquals(true, movieDao.getMovies().size == movieCount)
    }

    @Test
    fun findMovieByTitle() = runBlocking {
        val title = "Avengers:Civil War"
        val movie = TestUtils.createMovie(1, title)
        movieDao.addMovies(listOf(movie))
        assertEquals(true, movieDao.findMovieByTitle(title) != null)
    }

    @Test
    fun findMoviesByTitle() = runBlocking {
        movieDao.addMovies(TestUtils.createMovies(10))
        val foundMovies = movieDao.findMoviesByTitle("%movie%", 5, 5)
        assertEquals(true, foundMovies.size == 5)
    }
}