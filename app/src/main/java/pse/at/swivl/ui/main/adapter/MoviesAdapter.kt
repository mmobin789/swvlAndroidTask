package pse.at.swivl.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_movies_list.*
import pse.at.swivl.R
import pse.at.swivl.ui.main.domain.models.Movie

class MoviesAdapter(private val list: List<Movie>) :
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
        holder.tvCast.text = movie.cast.joinToString()
        holder.tvGenre.text = movie.genres.joinToString()
        holder.ratingBar.rating = movie.rating.toFloat()

        /*      Pixel.load(
                  picture.getImageURL(),
                  imageView = holder.iv,
                  pixelOptions = PixelOptions.Builder()
                      .setPlaceholderResource(R.drawable.ic_loading_android).build()
              )
      */

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        init {
            itemView.setOnClickListener {

            }
        }
    }
}