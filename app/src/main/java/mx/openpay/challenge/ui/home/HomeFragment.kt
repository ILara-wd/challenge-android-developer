package mx.openpay.challenge.ui.home

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
import mx.openpay.challenge.databinding.FragmentHomeBinding
import mx.openpay.challenge.ui.adapter.MovieListAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel.getPopularMovie()
        observers()
        return binding.root
    }

    private fun observers() {
        homeViewModel.genreByPopularMovie.observe(this) {
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
        homeViewModel.message.observe(this) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        homeViewModel.isLoading.observe(this) {
            binding.popularProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        homeViewModel.popularMovie.observe(this) { movies ->
            binding.popularRecyclerView.apply {
                adapter = MovieListAdapter(movies)
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }


        }
        homeViewModel.topRatedMovie.observe(this) { movies ->
            binding.inTheatersRecyclerView.apply {
                adapter = MovieListAdapter(movies)
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }


        }
        homeViewModel.discoveryMovie.observe(this) { movies ->
            binding.upcomingRecyclerView.apply {
                adapter = MovieListAdapter(movies)
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }


        }
        homeViewModel.firstPopularMovie.observe(this) { movie ->
            showMoviePopular(movie)
        }
    }

    private fun showMoviePopular(movie: Movie?) {
        if (movie != null) {
            with(binding) {
                homeViewModel.getGenreByPopularMovie()
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
