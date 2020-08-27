package pse.at.swivl.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main_fragment.*
import pse.at.swivl.R
import pse.at.swivl.ui.main.adapter.MoviesAdapter
import pse.at.swivl.ui.main.domain.models.Movie
import pse.at.swivl.ui.main.domain.models.MoviePicture

class MainFragment : Fragment(), SearchView.OnQueryTextListener, MainViewModel.View {

    private lateinit var moviesAdapter: MoviesAdapter

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadMovies()
            it.searchPhotos("Margot robbie") { l ->
                Toast.makeText(view.context, l.size.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        sv.setOnQueryTextListener(this)


    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText.isNullOrBlank())
            viewModel.loadMovies()
        else
            viewModel.findMoviesByTitle(newText, 5, 5)
        return true
    }

    override fun onMoviesLoaded(movies: List<Movie>) {
        rv.adapter = MoviesAdapter(movies)
    }

    override fun onMoviePictureLoaded(moviePictures: List<MoviePicture>) {

    }

}