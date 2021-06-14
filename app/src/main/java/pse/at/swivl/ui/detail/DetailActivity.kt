package pse.at.swivl.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pse.at.swivl.databinding.ActivityDetailBinding
import pse.at.swivl.databinding.AdapterMoviesListBinding
import pse.at.swivl.ui.detail.adapter.PicturesAdapter
import pse.at.swivl.ui.movies.domain.models.Movie
import pse.at.swivl.ui.movies.domain.models.MoviePicture

class DetailActivity : AppCompatActivity(), DetailViewModel.View {


    private val viewModel by lazy {
        ViewModelProvider(this)[DetailViewModel::class.java]
    }

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.let { vm ->
            vm.attachView(this@DetailActivity)
            vm.addObservers(this@DetailActivity)
            intent.getParcelableExtra<Movie>("movie")?.let {
                binding.layoutAdapterMovies.run {
                    vm.searchMoviePictures(it.title)
                    tvTitle.text = it.title
                    tvYear.text = it.year
                    tvCast.text = it.cast.joinToString()
                    tvGenre.text = it.genres.joinToString()
                    ratingBar.rating = it.rating.toFloat()
                }
            }
        }

    }

    override fun onMoviePictureLoaded(moviePictures: List<MoviePicture>) {
        binding.rv.adapter = PicturesAdapter(moviePictures)
    }
}