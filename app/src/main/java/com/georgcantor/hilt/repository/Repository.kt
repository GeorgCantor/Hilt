package com.georgcantor.hilt.repository

import com.georgcantor.hilt.R
import com.georgcantor.hilt.model.data.Article
import com.georgcantor.hilt.model.local.NewsDatabase
import com.georgcantor.hilt.model.remote.ApiService
import com.georgcantor.hilt.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsDatabase: NewsDatabase,
    private val apiService: ApiService
) {

    fun getNews(): Flow<ApiResponse<List<Article>>> = flow {
        val articles = newsDatabase.newsDao().getArticles()
        val response = apiService.getNewsAsync("usa", 1)
        when (response.isSuccessful) {
            true -> {
                if (response.body() != null) {
                    newsDatabase.newsDao().insertAll(response.body()?.articles)
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Success(articles))
                }
            }
            false -> emit(ApiResponse.Error(R.string.no_internet, articles))
        }
    } as Flow<ApiResponse<List<Article>>>
}