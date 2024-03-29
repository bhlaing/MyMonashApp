package com.billy.mymonashapp.application.shared

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
* Filter out nulls from liveData.value
*/
fun <T> LifecycleOwner.observeNonNull(liveData: LiveData<T>, f: (T) -> Unit) {
    liveData.observe(this, { t -> t?.let(f) })
}

inline fun <T> LifecycleOwner.observe(liveData: LiveData<T>, crossinline action: (t: T?) -> Unit) {
    liveData.observe(this, { action(it) })
}