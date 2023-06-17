package mx.openpay.challenge.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.model.entity.Genre
import mx.openpay.challenge.data.model.entity.Movie
import javax.inject.Inject

class Service @Inject constructor(private val api: ApiClient) {

    suspend fun getPopularMovie(page: Int?): List<Movie> =
        withContext(Dispatchers.IO) {
            val response = api.movieServiceFetchPopularList(page)
            response.execute().body()?.results.orEmpty()
        }

    suspend fun getGetAllGenreMovie(): List<Genre> =
        withContext(Dispatchers.IO) {
            val response = api.movieServiceFetchMovieGenreList()
            response.execute().body()?.results.orEmpty()
        }

    suspend fun getTopRatedMovie(page: Int?): List<Movie> =
        withContext(Dispatchers.IO) {
            val response = api.movieServiceFetchTopRatedList(page)
            response.execute().body()?.results.orEmpty()
        }

    suspend fun getDiscoverMovie(page: Int?): List<Movie> =
        withContext(Dispatchers.IO) {
            val response = api.movieServiceFetchDiscoverList(page)
            response.execute().body()?.results.orEmpty()
        }

}
