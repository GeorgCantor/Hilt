package com.georgcantor.hilt.util

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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