package com.georgcantor.hilt.ui.fragment.news

import android.view.LayoutInflater.from
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat.setTransitionName
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.georgcantor.hilt.databinding.ItemNewsBinding
import com.georgcantor.hilt.model.data.Article
import com.georgcantor.hilt.util.Constants.IMAGE_TRANSACTION_NAME
import javax.inject.Inject

class NewsAdapter @Inject constructor() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var onItemClick: ((Article, ImageView) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article, ImageView) -> Unit) {
        onItemClick = listener
    }

    private val itemCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    private val differ = AsyncListDiffer(this, itemCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(from(parent.context), parent, false))
    }

    var articles: List<Article>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size

    inner class NewsViewHolder(private val itemNewsBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemNewsBinding.root) {
        fun bind(article: Article) {
            itemNewsBinding.apply {
                this.article = article
                setTransitionName(ivArticleImg, article.urlToImage ?: IMAGE_TRANSACTION_NAME)
                executePendingBindings()
                root.setOnClickListener { onItemClick?.invoke(article, ivArticleImg) }
            }
        }
    }
}