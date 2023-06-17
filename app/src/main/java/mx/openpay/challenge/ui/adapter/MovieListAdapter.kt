package mx.openpay.challenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.model.entity.Movie
import mx.openpay.challenge.databinding.ListItemMovieBinding

class MovieListAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount() = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {
            with(movieList[position]) {
                binding.titleText.text = title
                Glide.with(holder.itemView.context)
                    .load(ChallengeConstant.getPosterUrl(posterPath.orEmpty()))
                    .into(binding.image)
                holder.itemView.setOnClickListener {}
            }
        }
    }

    inner class MovieViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

}