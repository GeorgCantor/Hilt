package com.georgcantor.hilt.ui.fragment.news

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgcantor.hilt.model.data.Article
import com.georgcantor.hilt.repository.Repository
import com.georgcantor.hilt.util.ApiResponse
import com.georgcantor.hilt.util.bind

class NewsViewModel @ViewModelInject constructor(
    repository: Repository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val news = MutableLiveData<ApiResponse<List<Article>>>()

    init {
        repository.getNews().bind(viewModelScope, news)
    }
}