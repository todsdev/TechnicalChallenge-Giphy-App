package com.tods.giphy_project.repository

import com.tods.giphy_project.data.local.GiphyDAO
import com.tods.giphy_project.data.model.GiphyResponse
import com.tods.giphy_project.data.model.giphy.GiphyModel
import com.tods.giphy_project.data.remote.GiphyAPI
import com.tods.giphy_project.util.Constants.API_KEY
import com.tods.giphy_project.util.Constants.LANG
import com.tods.giphy_project.util.Constants.LIMIT
import com.tods.giphy_project.util.Constants.OFF_SET
import com.tods.giphy_project.util.Constants.RATING
import javax.inject.Inject

class GiphyRepository @Inject constructor(
    private val api: GiphyAPI,
    private val dao: GiphyDAO
) {
    suspend fun getGifsByTrending() = api.getGifsByTrending(API_KEY, LIMIT, RATING)
    suspend fun getGifsBySearch(query: String) = api.getGifsBySearch(API_KEY, query, LIMIT, OFF_SET, RATING, LANG)
    suspend fun insert(giphy: GiphyModel) = dao.insert(giphy)
    suspend fun delete(giphy: GiphyModel) = dao.delete(giphy)
    fun getAll() = dao.getAll()
}