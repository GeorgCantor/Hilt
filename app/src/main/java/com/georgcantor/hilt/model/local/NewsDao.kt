package com.georgcantor.hilt.model.local

import androidx.room.*

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Delete
    suspend fun delete(article: Article)

    @Query("select * from article")
    suspend fun getArticles(): List<Article>

    @Query("DELETE FROM article")
    suspend fun deleteAll()
}