package com.tods.giphy_project.data.model.giphy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "giphy")
data class GiphyModel(
    @PrimaryKey
    @SerializedName("id")
    var id : String,
    @SerializedName("images")
    val images : ImagesModel
)