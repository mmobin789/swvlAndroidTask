package pse.at.swivl.ui.base

import androidx.room.Room
import pse.at.swivl.TestApp
import pse.at.swivl.database.OfflineDatabase

abstract class AppRepository {
    companion object {
        private val appContext = TestApp.getInstance()
        private val offlineDatabase =
            Room.databaseBuilder(appContext, OfflineDatabase::class.java, "Test").build()

        fun getOfflineDatabase() = offlineDatabase
    }
}