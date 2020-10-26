package com.georgcantor.hilt.util

import androidx.fragment.app.FragmentFactory
import com.georgcantor.hilt.ui.fragment.news.NewsAdapter
import com.georgcantor.hilt.ui.fragment.news.NewsFragment
import javax.inject.Inject

class NewsFragmentFactory @Inject constructor(
    private val newsAdapter: NewsAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) = when (className) {
        NewsFragment::class.java.name -> NewsFragment(newsAdapter)
        else -> super.instantiate(classLoader, className)
    }
}