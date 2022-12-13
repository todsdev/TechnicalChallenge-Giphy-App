package com.tods.giphy_project.data.model.giphy

import com.google.gson.annotations.SerializedName

data class ImagesModel(
    @SerializedName("original")
    val original : ImageDetailsModel,
    @SerializedName("downsized")
    val downsized : ImageDetailsModel
)