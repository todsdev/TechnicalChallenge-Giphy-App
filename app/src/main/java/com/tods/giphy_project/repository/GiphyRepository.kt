package com.tods.giphy_project.repository

import com.tods.giphy_project.data.remote.GiphyAPI
import com.tods.giphy_project.util.Constants.API_KEY
import com.tods.giphy_project.util.Constants.LIMIT
import com.tods.giphy_project.util.Constants.RATING
import javax.inject.Inject

class GiphyRepository @Inject constructor(
    val api: GiphyAPI
) {
    suspend fun getGifsByTrending() = api.getGifsByTrending(API_KEY, LIMIT, RATING)
}