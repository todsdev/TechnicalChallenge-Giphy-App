package com.tods.giphy_project.data.model.giphy

import com.google.gson.annotations.SerializedName


data class GiphyModel(
    @SerializedName("id")
    val id : String,
    @SerializedName("images")
    val images : ImagesModel
)