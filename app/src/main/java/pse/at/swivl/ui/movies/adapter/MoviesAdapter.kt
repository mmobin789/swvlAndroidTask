package pse.at.swivl.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_movies_list.*
import pse.at.swivl.R
import pse.at.swivl.ui.movies.domain.models.Movie

class MoviesAdapter(
    private val list: List<Movie>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_movies_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = list[position]
        holder.tvTitle.text = movie.title
        holder.tvYear.text = movie.year
        holder.ratingBar.rating = movie.rating.toFloat()

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class VH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            rootL.setOnClickListener {
                onClickListener.onClick(list[adapterPosition])
            }

            tvCast.visibility = View.GONE
            tvGenre.visibility = View.GONE
            ratingBar.setOnClickListener { }

        }


    }

    interface OnClickListener {
        fun onClick(movie: Movie)
    }
}