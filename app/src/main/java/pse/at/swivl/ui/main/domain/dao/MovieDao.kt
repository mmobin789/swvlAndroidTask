package pse.at.swivl.ui.main.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pse.at.swivl.ui.main.domain.Movie


@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    suspend fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)
}