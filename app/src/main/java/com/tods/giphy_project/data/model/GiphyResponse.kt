package com.tods.giphy_project.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.tods.giphy_project.data.model.giphy.GiphyModel

class GiphyResponse(
    val data : List<GiphyModel>
)