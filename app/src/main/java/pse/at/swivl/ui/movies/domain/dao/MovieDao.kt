package pse.at.swivl.ui.movies.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pse.at.swivl.ui.movies.domain.models.Movie


@Dao
interface MovieDao {
    /**
     * This query should be improved using offset to gain paginated data snapshot there's no point of loading all the table at once because it performs poorly
     * and most likely all contents of the table will never be viewed.
     * There's no point in loading all the data at once user will not go through it
     * Limit ensures performance around constraints.
     */
    @Query("SELECT * FROM Movie ORDER BY year DESC LIMIT 500")
    fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movies: List<Movie>): List<Long>

    /**
     * The limit forces the user to type more to gain accurate results instead of returning all data from the table which gives bad performance.
     */
    @Query("SELECT * FROM Movie WHERE title LIKE :title ORDER BY year DESC LIMIT 100")
    fun findMoviesByTitle(title: String): List<Movie>

    /*  @Query("SELECT * FROM MOVIE WHERE title=:title")
      fun findMovieByTitle(title: String): Movie?*/

/*    @Query("SELECT * FROM MOVIE WHERE id=:id")
    fun findMovieById(id: Int): Movie?*/
}