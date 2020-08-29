package pse.at.swivl.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.adapter_movies_list.*
import pse.at.swivl.R
import pse.at.swivl.ui.detail.adapter.PicturesAdapter
import pse.at.swivl.ui.movies.domain.models.MoviePicture

class DetailActivity : AppCompatActivity(), DetailViewModel.View {

    private val viewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.getStringExtra("title")?.run {

            tvTitle.text = this

            viewModel.let { vm ->
                vm.attachView(this@DetailActivity)
                vm.addObservers(this@DetailActivity)
                vm.searchMoviePictures(this)
                vm.findMovieByTitle(this) {
                    tvYear.text = it.year
                    tvCast.text = it.cast.joinToString()
                    tvGenre.text = it.genres.joinToString()
                    ratingBar.rating = it.rating.toFloat()
                }
            }

        }
    }

    override fun onMoviePictureLoaded(moviePictures: List<MoviePicture>) {
        rv.adapter = PicturesAdapter(moviePictures)
    }
}