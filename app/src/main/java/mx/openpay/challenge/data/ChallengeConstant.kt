package mx.openpay.challenge.data

object ChallengeConstant {

    const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY_NAME = "api_key"
    const val API_KEY_VALUE = "8c3e19ab75799925c7e1fe459a9e72f2"

    const val PLACE_COLLECTION = "place"

    const val MILLISECONDS_LOCATIONS: Long = 300000
    const val MILLISECONDS_INTERVAL: Long = 1000

    const val API_VERSION: Int = 3
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    const val BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185"
    const val BASE_YT_IMG_URL = "https://img.youtube.com/vi/"
    const val BASE_YT_WATCH_URL = "https://www.youtube.com/watch?v="
    const val MAX_RATING = 10f

    fun getBackdropUrl(path: String) = BASE_BACKDROP_URL + path
    fun getPosterUrl(path: String) = BASE_POSTER_URL + path
    fun getProfileUrl(path: String) = BASE_PROFILE_URL + path
    fun getYoutubeImageUrl(youtubeId: String) = "$BASE_YT_IMG_URL$youtubeId/hqdefault.jpg"
    fun getYoutubeWatchUrl(youtubeId: String) = "$BASE_YT_WATCH_URL$youtubeId"/**/
    fun getRuntimeDateFormat() = ("yyyy-MM-dd")

}