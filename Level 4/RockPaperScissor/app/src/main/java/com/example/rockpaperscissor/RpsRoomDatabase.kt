package com.example.rockpaperscissor

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RockPaperScissor::class], version = 1, exportSchema = false)
abstract class RpsRoomDatabase : RoomDatabase(){

    abstract fun rpsDao() : RpsDao

    companion object{

        private const val DATABASE_NAME = "GAMEHISTORY"

        @Volatile
        private var rpsInstance : RpsRoomDatabase? = null

        fun getDatabase(context: Context) : RpsRoomDatabase? {
            if (rpsInstance == null){
                synchronized(RpsRoomDatabase::class.java){
                    if (rpsInstance == null){
                        rpsInstance = Room.databaseBuilder(context.applicationContext, RpsRoomDatabase::class.java,
                            DATABASE_NAME).build()
                    }
                }
            }
            return rpsInstance
        }

    }

}