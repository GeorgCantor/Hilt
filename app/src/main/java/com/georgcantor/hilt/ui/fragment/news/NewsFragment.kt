package com.georgcantor.hilt.ui.fragment.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.georgcantor.hilt.databinding.FragmentNewsBinding
import com.georgcantor.hilt.model.data.Article
import com.georgcantor.hilt.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment constructor(
    private val adapter: NewsAdapter,
    private var viewModel: NewsViewModel? = null
) : Fragment() {

    private lateinit var bindingLayout: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentNewsBinding.inflate(layoutInflater, container, false)
        viewModel = viewModel ?: ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)
        this.bindingLayout = view
        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingLayout.list.adapter = adapter

        postponeEnterTransition()
        bindingLayout.list.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }

        adapter.setOnItemClickListener { article, imageView ->

        }

        viewModel?.news?.observe(viewLifecycleOwner) { it: ApiResponse<List<Article>> ->
            it.onProgress {
                bindingLayout.list.gone()
                bindingLayout.loadingDialog.visible()
            }.onSuccess {
                if (it.isEmpty()) {
                    bindingLayout.noDataAlert.visible()
                    bindingLayout.list.gone()
                } else {
                    bindingLayout.list.visible()
                }
                bindingLayout.loadingDialog.gone()
                adapter.articles = it
            }.onError { _: Int, list: List<Article>? ->
                adapter.articles = list ?: emptyList()
                bindingLayout.loadingDialog.gone()
            }
        }
    }
}