package mx.openpay.challenge.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.openpay.challenge.data.model.entity.Movie

@Entity(tableName = "movie_table")
class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "voteCount") val voteCount: Int,
    @ColumnInfo(name = "posterPath") val posterPath: String
)

fun Movie.toDatabase() =
    MovieEntity(
        title = title.orEmpty(),
        posterPath = posterPath.orEmpty(),
        voteCount = voteCount ?: 0
    )
