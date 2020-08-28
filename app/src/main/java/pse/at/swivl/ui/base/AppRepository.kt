package pse.at.swivl.ui.base

import androidx.room.Room
import pse.at.swivl.TestApp
import pse.at.swivl.database.OfflineDatabase

/**
 * An abstract database repository defining this app's repositories in sharing common needs and behavior.
 */
abstract class AppRepository {
    protected val context = TestApp.getInstance()

    companion object {
        private var offlineDatabase: OfflineDatabase? = null

        fun getOfflineDatabase(): OfflineDatabase {
            if (offlineDatabase == null)
                offlineDatabase =
                    Room.databaseBuilder(TestApp.getInstance(), OfflineDatabase::class.java, "Test")
                        .build()
            return offlineDatabase!!
        }
    }
}