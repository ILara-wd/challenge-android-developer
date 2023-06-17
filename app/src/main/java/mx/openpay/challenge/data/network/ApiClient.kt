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


    @GET("/${ChallengeConstant.API_VERSION}/movie/upcoming")
    fun movieServiceFetchUpcomingList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/now_playing")
    fun movieServiceFetchInTheatersList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/{id}")
    fun movieServiceFetchDetails(@Path("id") id: Int): Call<Movie>

    @GET("/${ChallengeConstant.API_VERSION}/movie/{id}/credits")
    fun movieServiceFetchCredits(@Path("id") id: Int): Call<CreditsResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/{id}/videos")
    fun movieServiceFetchVideos(@Path("id") id: Int): Call<VideosResponse>

    @GET("/${ChallengeConstant.API_VERSION}/discover/tv")
    fun tvServiceFetchDiscoveryList(@Query("page") page: Int): Call<TvDiscoverResponse>

    @GET("/${ChallengeConstant.API_VERSION}/tv/{id}")
    fun tvServiceFetchDetails(@Path("id") id: Int): Call<TvShowDetails>

    @GET("/${ChallengeConstant.API_VERSION}/tv/{id}/credits")
    fun tvServiceFetchCredits(@Path("id") id: Int): Call<CreditsResponse>

    @GET("/${ChallengeConstant.API_VERSION}/tv/{id}/videos")
    fun tvServiceFetchVideos(@Path("id") id: Int): Call<VideosResponse>

    @GET("/${ChallengeConstant.API_VERSION}/person/{id}")
    fun peopleServiceFetchDetails(@Path("id") id: Int): Call<Person>

    @GET("/${ChallengeConstant.API_VERSION}/person/{id}/images")
    fun peopleServiceFetchImages(@Path("id") id: Int): Call<PersonImagesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/person/{id}/combined_credits")
    fun peopleServiceFetchCredits(@Path("id") id: Int): Call<PersonCreditsResponse>

}
