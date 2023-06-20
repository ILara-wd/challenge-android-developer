package mx.openpay.challenge.domain

import mx.openpay.challenge.data.Repository
import mx.openpay.challenge.data.database.entity.toDatabase
import mx.openpay.challenge.data.model.entity.Movie
import javax.inject.Inject

class PopularMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(page: Int?): List<Movie> {
        val quotes = repository.doGetPopularMovie(page)
        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }
}