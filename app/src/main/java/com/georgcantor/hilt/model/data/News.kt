package com.georgcantor.hilt.model.data

data class News(
    var status: String,
    var totalResults: Int,
    var articles: List<Article>
)