package pse.at.swivl.ui.base

import androidx.room.Room
import pse.at.swivl.TestApp
import pse.at.swivl.api.NetworkClient
import pse.at.swivl.database.OfflineDatabase

/**
 * An abstract repository defining this app's repositories in sharing common needs and behavior.
 */
abstract class AppRepository {
    protected val context = TestApp.getInstance()
    protected val apiClient = NetworkClient.getApiClient()

    companion object {
        private val offlineDatabase =
            Room.databaseBuilder(TestApp.getInstance(), OfflineDatabase::class.java, "Test").build()

        fun getOfflineDatabase() = offlineDatabase
    }
}