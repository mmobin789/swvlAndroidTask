package pse.at.swivl.ui.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_pictures_list.*
import pse.at.swivl.R
import pse.at.swivl.ui.movies.domain.models.MoviePicture
import pse.at.swivl.ui.utils.load

class PicturesAdapter(
    private val list: List<MoviePicture>
) :
    RecyclerView.Adapter<PicturesAdapter.VH>() {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_pictures_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = list[position]
        holder.iv.load(movie.getImageURL())

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer
}