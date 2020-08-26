package pse.at.swivl.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pse.at.swivl.R

class MainFragment : Fragment() {

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

        viewModel.loadMovies(view.context) {
            Toast.makeText(view.context, it.size.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModel.searchPhotos("Margot robbie") {
            Toast.makeText(view.context, it.size.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}