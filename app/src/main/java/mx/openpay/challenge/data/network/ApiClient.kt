package mx.openpay.challenge.data.network

import mx.openpay.challenge.data.ChallengeConstant
import mx.openpay.challenge.data.model.network.MoviesResponse
import mx.openpay.challenge.data.model.entity.Movie
import mx.openpay.challenge.data.model.network.GenresResponse
import mx.openpay.challenge.data.model.network.TvDiscoverResponse
import mx.openpay.challenge.data.model.entity.TvShowDetails
import mx.openpay.challenge.data.model.network.CreditsResponse
import mx.openpay.challenge.data.model.network.VideosResponse
import mx.openpay.challenge.data.model.entity.Person
import mx.openpay.challenge.data.model.network.PersonImagesResponse
import mx.openpay.challenge.data.model.network.PersonCreditsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("/${ChallengeConstant.API_VERSION}/genre/movie/list")
    fun movieServiceFetchMovieGenreList(): Call<GenresResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/popular")
    fun movieServiceFetchPopularList(
        @Query("page") page: Int?
    ): Call<MoviesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/top_rated")
    fun movieServiceFetchTopRatedList(
        @Query("page") page: Int?
    ): Call<MoviesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/upcoming")
    fun movieServiceFetchDiscoverList(
        @Query("page") page: Int?
    ): Call<MoviesResponse>

}
