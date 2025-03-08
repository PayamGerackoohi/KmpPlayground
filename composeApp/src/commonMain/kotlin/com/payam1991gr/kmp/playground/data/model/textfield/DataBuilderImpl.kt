package com.payam1991gr.kmp.playground.data.model.textfield

class DataBuilderImpl<T> : DataBuilder<T> {
    private var hasError = false

    override fun error(): Nothing? {
        hasError = true
        return null
    }

    fun build(data: T): Data<T> = Data(data, hasError)
}
