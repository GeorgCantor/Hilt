package com.georgcantor.hilt.model.remote

import com.georgcantor.hilt.BuildConfig
import com.georgcantor.hilt.model.data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNewsAsync(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): News
}