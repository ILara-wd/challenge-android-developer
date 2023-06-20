package mx.openpay.challenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.openpay.challenge.data.database.dao.MovieDao
import mx.openpay.challenge.data.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
