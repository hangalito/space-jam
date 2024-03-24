package com.hangalo.spacejam.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hangalo.spacejam.model.AstronomicPicture


@Database(entities = [AstronomicPicture::class], version = 1, exportSchema = false)
abstract class SpaceJamDatabase : RoomDatabase() {
    abstract fun astronomicPictureDao(): AstronomicPictureDao

    companion object {
        @Volatile
        private var Instance: SpaceJamDatabase? = null

        fun getInstance(context: Context): SpaceJamDatabase {
            return Instance ?: synchronized(this) {
                Instance ?: Room
                    .databaseBuilder(context, SpaceJamDatabase::class.java, "space_jam_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
