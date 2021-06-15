package pse.at.swivl.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import pse.at.swivl.databinding.MainFragmentBinding
import pse.at.swivl.ui.DI
import pse.at.swivl.ui.detail.DetailActivity
import pse.at.swivl.ui.movies.adapter.MoviesAdapter
import pse.at.swivl.ui.movies.domain.models.Movie
import pse.at.swivl.ui.movies.domain.models.MoviesUI

class MoviesFragment : Fragment(),
    MoviesAdapter.OnClickListener, SearchView.OnQueryTextListener {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    val binding by lazy {
        MainFragmentBinding.inflate(layoutInflater)
    }

    private val viewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        DI.start(view.context)

        viewModel.let {
            it.loadMovies()
            it.getMoviesUiData().observe(viewLifecycleOwner) { ui ->
                when (ui) {
                    MoviesUI.Loading -> {
                        binding.pBar.visibility = View.VISIBLE
                    }
                    is MoviesUI.Success -> {
                        binding.pBar.visibility = View.GONE
                        binding.rv.adapter = MoviesAdapter(ui.movies, this)
                    }
                }
            }
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

    override fun onClick(movie: Movie) {
        startActivity(Intent(context, DetailActivity::class.java).putExtra("movie", movie))
    }

}