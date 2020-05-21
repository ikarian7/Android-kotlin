package com.example.level5_task2.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.level5_task2.model.Games

@Dao
interface GameDao {

    @Query("SELECT * FROM gamesTable ORDER BY releaseDate ASC")
    fun getAllGames() : LiveData<List<Games>>

    @Insert
    suspend fun insertGame(game: Games)

    @Delete
    suspend fun deleteGame(game: Games)

    @Update
    suspend fun updateGame(game: Games)

    @Query("DELETE FROM gamesTable")
    suspend fun deleteAllGames()
}