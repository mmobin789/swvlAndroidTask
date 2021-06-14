package pse.at.swivl.ui.movies.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pse.at.swivl.ui.movies.domain.models.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie ORDER BY year DESC")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movies: List<Movie>): List<Long>

    @Query("SELECT * FROM MOVIE WHERE title LIKE :title AND rating =:rating ORDER BY year DESC LIMIT :maxResults")
    fun findMoviesByTitle(title: String, maxResults: Int, rating: Int): List<Movie>

    @Query("SELECT * FROM MOVIE WHERE title=:title")
    fun findMovieByTitle(title: String): Movie?

/*    @Query("SELECT * FROM MOVIE WHERE id=:id")
    fun findMovieById(id: Int): Movie?*/
}