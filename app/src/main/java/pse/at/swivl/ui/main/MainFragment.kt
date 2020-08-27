package pse.at.swivl.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main_fragment.*
import pse.at.swivl.R
import pse.at.swivl.ui.detail.DetailActivity
import pse.at.swivl.ui.main.adapter.MoviesAdapter
import pse.at.swivl.ui.main.domain.models.Movie

class MainFragment : Fragment(), SearchView.OnQueryTextListener, MainViewModel.View,
    MoviesAdapter.OnClickListener {

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
        rv.adapter = MoviesAdapter(movies, this)
    }

    override fun onClick(movie: Movie) {
        startActivity(Intent(context, DetailActivity::class.java).putExtra("title", movie.title))
    }

}