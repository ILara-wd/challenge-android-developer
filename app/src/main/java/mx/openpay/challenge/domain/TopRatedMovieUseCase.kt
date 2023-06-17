package mx.openpay.challenge.domain

import mx.openpay.challenge.data.Repository
import javax.inject.Inject

class TopRatedMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(page: Int?) = repository.doGetTopRatedMovieMovie(page)
}