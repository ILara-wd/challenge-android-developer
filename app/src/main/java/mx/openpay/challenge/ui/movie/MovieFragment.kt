package mx.openpay.challenge.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import mx.openpay.challenge.R
import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.model.entity.Movie
import mx.openpay.challenge.databinding.FragmentMovieBinding
import mx.openpay.challenge.ui.movie.adapter.MovieListAdapter

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        movieViewModel.getPopularMovie()
        observers()
        return binding.root
    }

    private fun observers() {
        movieViewModel.genreByPopularMovie.observe(this) {
            var genreList = ""
            it.forEachIndexed { index, genre ->
                if (index == 0) {
                    binding.hlMovieGenrePrimary.text = genre.name
                } else {
                    genreList += "${genre.name} / "
                }
            }
            binding.hlMovieGenreSecondary.text = genreList
        }
        movieViewModel.message.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        movieViewModel.isLoading.observe(this) {
            binding.popularProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        movieViewModel.popularMovie.observe(this) { movies ->
            setAdapterToRecyclerView(binding.popularRecyclerView, movies)
        }
        movieViewModel.topRatedMovie.observe(this) { movies ->
            setAdapterToRecyclerView(binding.inTheatersRecyclerView, movies)
        }
        movieViewModel.discoveryMovie.observe(this) { movies ->
            setAdapterToRecyclerView(binding.upcomingRecyclerView, movies)
        }
        movieViewModel.firstPopularMovie.observe(this) { movie ->
            showMoviePopular(movie)
        }
    }

    private fun setAdapterToRecyclerView(recyclerView: RecyclerView, movies: List<Movie>) {
        recyclerView.apply {
            adapter = MovieListAdapter(movies)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun showMoviePopular(movie: Movie?) {
        if (movie != null) {
            with(binding) {
                movieViewModel.getGenreByPopularMovie()
                hlNumOfVotes.text =
                    requireActivity().getString(R.string.votes, movie.voteCount.toString())
                hlMovieTitle.text = movie.title
                hlRatingBar.rating = (5 * (movie.voteAverage / ChallengeConstant.MAX_RATING))
                Glide.with(requireContext())
                    .load(ChallengeConstant.getBackdropUrl(movie.posterPath.orEmpty()))
                    .into(binding.hlMovieImage)
            }
        }
    }

}
