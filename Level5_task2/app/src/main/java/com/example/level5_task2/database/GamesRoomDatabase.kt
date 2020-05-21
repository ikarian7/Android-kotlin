package com.example.level5_task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.level5_task2.model.Games


@Database(entities = [Games::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameRoomDatabase: RoomDatabase(){
    abstract fun gameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DATABASE"

        @Volatile
        private var INSTANCE: GameRoomDatabase? = null

        fun getDatabase(context: Context): GameRoomDatabase? {
            if (INSTANCE == null) {
                kotlin.synchronized(GameRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                                context.applicationContext,
                                GameRoomDatabase::class.java, DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}