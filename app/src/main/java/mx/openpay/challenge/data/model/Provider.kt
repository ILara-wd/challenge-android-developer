package mx.openpay.challenge.data.model

import mx.openpay.challenge.data.model.entity.Genre
import mx.openpay.challenge.data.model.entity.Movie
import mx.openpay.challenge.data.model.entity.Place
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Provider @Inject constructor() {

    var popularMovie: List<Movie>? = null
    var discoverMovie: List<Movie>? = null
    var topRatedMovie: List<Movie>? = null
    var genreList: List<Genre> = emptyList()

    var placeLists: List<Place> = emptyList()
    var place: Place? = null

}
