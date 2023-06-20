package mx.openpay.challenge.data.model.entity

import com.google.gson.annotations.SerializedName
import mx.openpay.challenge.data.database.entity.MovieEntity

data class Movie(
    @SerializedName("id")
    var id: Int,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("vote_count")
    var voteCount: Int? = null,

    @SerializedName("vote_average")
    var voteAverage: Float? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("runtime")
    var runtime: Int? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("genres")
    var genres: List<Genre>? = null,
)

fun MovieEntity.toDomain() =
    Movie(title = title, posterPath = posterPath, voteCount = voteCount, id = id)