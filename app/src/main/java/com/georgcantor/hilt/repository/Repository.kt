package com.georgcantor.hilt.repository

import com.georgcantor.hilt.model.local.NewsDatabase
import com.georgcantor.hilt.model.remote.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsDatabase: NewsDatabase,
    private val apiService: ApiService
) {
}