package com.hangalo.spacejam.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.hangalo.spacejam.model.AstronomicPicture
import kotlinx.coroutines.flow.Flow


/**
 * Provides access to the [AstronomicPicture] object
 * responsible for database operations such as inserting,
 * deleting and getting the saved pictures
 */
@Dao
interface AstronomicPictureDao {

    /**
     * Fetches all the pictures from the database
     */
    @Query("SELECT * FROM astronomic_picture ORDER BY date DESC")
    fun getAll(): Flow<List<AstronomicPicture>>

    /**
     * Fetches a picture from the database by the given date
     */
    @Query("SELECT * FROM astronomic_picture WHERE date = :date")
    fun getByDate(date: String): Flow<AstronomicPicture>

    /**
     * Insert a picture into the database
     */
    @Insert
    suspend fun insert(astronomicPicture: AstronomicPicture)

    /**
     * Delete a picture from the database
     */
    @Delete
    suspend fun delete(astronomicPicture: AstronomicPicture)

}
