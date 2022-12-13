package com.tods.giphy_project.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tods.giphy_project.data.model.giphy.GiphyModel
import com.tods.giphy_project.data.model.giphy.ImagesModel

class GiphyConverters {
    @TypeConverter
    fun fromImagesModel(value: ImagesModel): String = Gson().toJson(value)

    @TypeConverter
    fun toImagesModel(value: String): ImagesModel = Gson().fromJson(value, ImagesModel::class.java)
}