package pse.at.swivl.ui.base

import androidx.room.Room
import pse.at.swivl.TestApp
import pse.at.swivl.database.OfflineDatabase

abstract class AppRepository {
    protected val context = TestApp.getInstance()

    companion object {
        private val offlineDatabase =
            Room.databaseBuilder(TestApp.getInstance(), OfflineDatabase::class.java, "Test").build()

        fun getOfflineDatabase() = offlineDatabase
    }
}