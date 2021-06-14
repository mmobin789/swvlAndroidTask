package pse.at.swivl.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import pse.at.swivl.databinding.ActivityDetailBinding
import pse.at.swivl.ui.detail.adapter.PicturesAdapter
import pse.at.swivl.ui.movies.domain.models.Movie
import pse.at.swivl.ui.movies.domain.models.MoviePicturesUI

class DetailActivity : AppCompatActivity() {


    private val viewModel: DetailViewModel by viewModel()

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.let { vm ->
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

            vm.getMoviePicturesData().observe(this) { ui ->
                when (ui) {
                    MoviePicturesUI.Loading -> {
                        binding.pBar.visibility = View.VISIBLE
                    }
                    is MoviePicturesUI.Success -> {
                        binding.pBar.visibility = View.GONE
                        binding.rv.adapter = PicturesAdapter(ui.movies)
                    }
                    is MoviePicturesUI.Failed -> {
                        binding.pBar.visibility = View.GONE
                        Toast.makeText(this, ui.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}