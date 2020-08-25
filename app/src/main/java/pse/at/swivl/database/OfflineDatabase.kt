package pse.at.swivl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pse.at.swivl.ui.main.domain.Movie
import pse.at.swivl.ui.main.domain.dao.MovieDao

@Database(entities = [Movie::class], version = 1)
@TypeConverters(pse.at.swivl.database.utils.TypeConverters::class)
abstract class OfflineDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}