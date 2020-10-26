package com.georgcantor.hilt.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.georgcantor.hilt.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun View.gone() { visibility = GONE }

fun View.visible() { visibility = VISIBLE }

fun <T> Flow<T>.bind(
    coroutineScope: CoroutineScope,
    liveData: MutableLiveData<T>,
    function: ((T) -> Unit)? = null
) {
    coroutineScope.launch {
        this@bind.collect {
            liveData.value = it
            if (function != null) function(it)
        }
    }
}

@BindingAdapter("imageUrl")
fun ImageView.load(link: String) = AppModule.provideGlide(this.context).load(link).into(this)

@BindingAdapter("hideIfEmpty")
fun TextView.hide(link: String?) {
    if (link.isNullOrEmpty()) visibility = GONE
}