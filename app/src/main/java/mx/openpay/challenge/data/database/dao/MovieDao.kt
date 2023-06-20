package mx.openpay.challenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.openpay.challenge.data.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY title DESC")
    suspend fun getAllMovie(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(quotes: List<MovieEntity>)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovie()

}
