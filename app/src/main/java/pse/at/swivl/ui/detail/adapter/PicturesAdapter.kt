package pse.at.swivl.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pse.at.swivl.R
import pse.at.swivl.databinding.AdapterPicturesListBinding
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
            AdapterPicturesListBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.adapter_pictures_list,
                    parent,
                    false
                )
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val movie = list[position]
        holder.binding.iv.load(movie.getImageURL())

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class VH(val binding: AdapterPicturesListBinding) : RecyclerView.ViewHolder(binding.root)
}