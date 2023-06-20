package mx.openpay.challenge.ui.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.model.entity.Movie
import mx.openpay.challenge.databinding.ListItemMovieBinding

class MovieListAdapter(val context: Context) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: Movie) {
            with(item) {
                binding.titleText.text = title
                Glide.with(context)
                    .load(ChallengeConstant.getPosterUrl(posterPath.orEmpty()))
                    .into(binding.image)
            }
        }
    }

}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
