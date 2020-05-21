package com.example.level5_task2.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.level5_task2.model.Games

class GamesRepository(context: Context){
    private var gameDao: GameDao?

    init {
        val gameRoomDatabase = GameRoomDatabase.getDatabase(context)
        gameDao = gameRoomDatabase?.gameDao()
    }

    fun getAllGames() : LiveData<List<Games>> {
        return gameDao?.getAllGames() ?: MutableLiveData(emptyList())
    }

    suspend fun insertGame(game: Games) {
        gameDao?.insertGame(game)
    }

    suspend fun deleteGame(game: Games) {
        gameDao?.deleteGame(game)
    }

    suspend fun updateGame(game: Games) {
        gameDao?.updateGame(game)
    }
    suspend fun deleteAllGames() = gameDao?.deleteAllGames()
}