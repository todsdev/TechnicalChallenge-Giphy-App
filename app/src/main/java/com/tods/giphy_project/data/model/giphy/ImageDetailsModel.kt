package com.tods.giphy_project.data.model.giphy

import com.google.gson.annotations.SerializedName

data class ImageDetailsModel(
    @SerializedName("url")
    val url : String
)