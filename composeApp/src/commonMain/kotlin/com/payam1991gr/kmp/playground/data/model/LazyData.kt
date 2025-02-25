package com.payam1991gr.kmp.playground.data.model

sealed interface LazyData<out T> {
    data class Data<T>(val data: T) : LazyData<T>
    data object Loading : LazyData<Nothing>
}
