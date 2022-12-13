package com.tods.giphy_project.data.local

import androidx.room.*
import com.tods.giphy_project.data.model.GiphyResponse
import com.tods.giphy_project.data.model.giphy.GiphyModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GiphyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(giphy: GiphyModel): Long

    @Delete
    suspend fun delete(giphy: GiphyModel)

    @Query("SELECT * FROM giphy ORDER BY id")
    fun getAll(): Flow<List<GiphyModel>>
}