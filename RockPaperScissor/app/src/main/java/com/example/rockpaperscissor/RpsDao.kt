package com.example.rockpaperscissor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RpsDao {

    @Query("SELECT * FROM history_table")
    suspend fun getAllGames(): List<RockPaperScissor>

    @Insert
    suspend fun insertGame(rockpaperscissor: RockPaperScissor)

    @Query("DELETE FROM history_table")
    suspend fun deleteAllGames()
}