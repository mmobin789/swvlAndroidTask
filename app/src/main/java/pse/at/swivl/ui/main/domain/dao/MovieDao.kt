package pse.at.swivl.ui.main.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pse.at.swivl.ui.main.domain.models.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    suspend fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)

    @Query("SELECT * FROM MOVIE WHERE title LIKE :title ORDER BY year LIMIT :maxResults")
    suspend fun findMoviesByTitle(title: String, maxResults: Int): List<Movie>
}