package com.example.rockpaperscissor

import android.content.Context

class RpsRepository (context: Context) {

    private val rpsDao: RpsDao

    init {
        val database = RpsRoomDatabase.getDatabase(context)
        rpsDao = database!!.rpsDao()
    }

    suspend fun getAllGames(): List<RockPaperScissor> {
        return rpsDao.getAllGames()
    }

    suspend fun insertGame(rps: RockPaperScissor) {
        rpsDao.insertGame(rps)
    }

    suspend fun deleteAllGames() {
        rpsDao.deleteAllGames()
    }
}
