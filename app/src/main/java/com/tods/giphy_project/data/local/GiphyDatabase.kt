package com.tods.giphy_project.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tods.giphy_project.data.model.GiphyResponse
import com.tods.giphy_project.data.model.giphy.GiphyModel

@Database(entities = [GiphyModel::class], version = 1, exportSchema = false)
@TypeConverters(GiphyConverters::class)
abstract class GiphyDatabase: RoomDatabase() {
    abstract fun giphyDao(): GiphyDAO
}