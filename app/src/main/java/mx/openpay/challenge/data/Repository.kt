package mx.openpay.challenge.data

import kotlinx.coroutines.flow.Flow
import mx.openpay.challenge.tools.DataState
import mx.openpay.challenge.data.model.Provider
import mx.openpay.challenge.data.model.entity.Genre
import mx.openpay.challenge.data.model.entity.Movie
import mx.openpay.challenge.data.model.entity.Place
import mx.openpay.challenge.data.network.Service
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Service,
    private val provider: Provider
) {

    suspend fun doGetPopularMovie(page: Int?): List<Movie> {
        val response = api.getPopularMovie(page)
        provider.popularMovie = response
        return response
    }

    suspend fun doGetDiscoverMovieMovie(page: Int?): List<Movie> {
        val response = api.getDiscoverMovie(page)
        provider.discoverMovie = response
        return response
    }

    suspend fun doGetTopRatedMovieMovie(page: Int?): List<Movie> {
        val response = api.getTopRatedMovie(page)
        provider.topRatedMovie = response
        return response
    }

    suspend fun doGetAllGenreMovie(): List<Genre> {
        val response = api.getGetAllGenreMovie()
        provider.genreList = response
        return response
    }

    suspend fun doGetAllPlace(): Flow<DataState<List<Place>>> {
        return api.getCollection()
    }

    suspend fun doSavePlaceFirestore(place: Place): Flow<DataState<Boolean>> {
        return api.savePlaceFirestore(place)
    }
}
