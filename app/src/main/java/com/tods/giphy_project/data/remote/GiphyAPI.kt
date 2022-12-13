package com.tods.giphy_project.data.remote

import com.tods.giphy_project.data.model.GiphyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {
    @GET("trending")
    suspend fun getGifsByTrending(
        @Query("api_key") key: String,
        @Query("limit") limit: Int,
        @Query("rating") rating: String
    ): Response<GiphyResponse>

    @GET("search")
    suspend fun getGifsBySearch(
        @Query("api_key") key: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): Response<GiphyResponse>
}