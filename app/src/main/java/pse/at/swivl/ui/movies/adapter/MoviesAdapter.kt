package pse.at.swivl.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pse.at.swivl.R
import pse.at.swivl.databinding.AdapterMoviesListBinding
import pse.at.swivl.ui.movies.domain.models.Movie

class MoviesAdapter(
    private val list: List<Movie>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            AdapterMoviesListBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_movies_list,
                    parent,
                    false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = list[position]
        holder.binding.run {
            tvTitle.text = movie.title
            tvYear.text = movie.year
            ratingBar.rating = movie.rating.toFloat()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VH(val binding: AdapterMoviesListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.run {
                root.setOnClickListener {
                    onClickListener.onClick(list[bindingAdapterPosition])
                }

                tvCast.visibility = View.GONE
                tvGenre.visibility = View.GONE
                ratingBar.setOnClickListener {
                    //prevents click gesture appearance on rating bar.
                }
            }

        }


    }

    interface OnClickListener {
        fun onClick(movie: Movie)
    }
}