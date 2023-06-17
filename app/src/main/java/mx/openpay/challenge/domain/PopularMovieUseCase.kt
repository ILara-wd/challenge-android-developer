package mx.openpay.challenge.domain

import mx.openpay.challenge.data.Repository
import javax.inject.Inject

class PopularMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(page: Int?) = repository.doGetPopularMovie(page)
}