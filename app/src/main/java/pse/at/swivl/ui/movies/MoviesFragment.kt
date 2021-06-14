package pse.at.swivl.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pse.at.swivl.databinding.MainFragmentBinding
import pse.at.swivl.ui.detail.DetailActivity
import pse.at.swivl.ui.movies.adapter.MoviesAdapter
import pse.at.swivl.ui.movies.domain.models.Movie

class MoviesFragment : Fragment(), SearchView.OnQueryTextListener, MoviesViewModel.View,
    MoviesAdapter.OnClickListener {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    val binding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.let {
            it.attachView(this)
            it.addObservers(this)
            it.loadMovies()
        }

        binding.sv.setOnQueryTextListener(this)


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
        binding.rv.adapter = MoviesAdapter(movies, this)
        binding.pBar.visibility = View.GONE
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(context, DetailActivity::class.java).putExtra("movie", movie))
    }

}