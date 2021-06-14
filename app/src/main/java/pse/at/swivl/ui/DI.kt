package pse.at.swivl.ui

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pse.at.swivl.api.NetworkClient
import pse.at.swivl.database.OfflineDatabase
import pse.at.swivl.ui.detail.DetailViewModel
import pse.at.swivl.ui.movies.MoviesViewModel
import pse.at.swivl.ui.movies.api.RemoteSource
import pse.at.swivl.ui.movies.repository.LocalSource
import pse.at.swivl.ui.movies.repository.MoviesRepository
import retrofit2.Retrofit

object DI {

    private var init = false

    /**
     * This initializes Koin DI for the whole application.
     * This must be only called once.
     */
    fun start(context: Context) {

        if (init)
            return

        val srcModule = module {
            single {
                NetworkClient.getRetrofit()
            }
            single { Gson() }
            single { get<Retrofit>().create(NetworkClient.API::class.java) }
            single { RemoteSource(get(), get()) }
            single { Room.databaseBuilder(get(), OfflineDatabase::class.java, "appDB").build() }
            factory { LocalSource(get<OfflineDatabase>().getMovieDao()) }
        }

        val moviesModule = module {
            factory { MoviesRepository(get(), get(), get(), get()) }
            viewModel { MoviesViewModel(get()) }
            viewModel { DetailViewModel(get()) }
        }



        startKoin {
            androidLogger()
            androidContext(context)
            modules(moviesModule, srcModule)
        }

        init = true


    }
}