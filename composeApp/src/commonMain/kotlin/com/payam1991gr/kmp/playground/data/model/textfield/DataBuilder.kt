package com.payam1991gr.kmp.playground.data.model.textfield

interface DataBuilder<T> {
    fun error(): Nothing?
}

fun <T> buildData(block: DataBuilder<T>.() -> T): Data<T> = DataBuilderImpl<T>().run {
    build(block())
}
