package pse.at.swivl.ui.main.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pse.at.swivl.ui.main.domain.models.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie ORDER BY year DESC")
    suspend fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>): List<Long>

    @Query("SELECT * FROM MOVIE WHERE title LIKE :title AND rating =:rating ORDER BY year DESC LIMIT :maxResults")
    suspend fun findMoviesByTitle(title: String, maxResults: Int, rating: Int): List<Movie>

    @Query("SELECT * FROM MOVIE WHERE title=:title")
    suspend fun findMovieByTitle(title: String): Movie?
}