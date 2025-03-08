package com.payam1991gr.kmp.playground.data.model.textfield

interface Converter<T> {
    fun from(data: T): String
    fun String.toData(): Data<T>
}

class IntConverter : Converter<Int> {
    override fun from(data: Int): String = data.toString()
    override fun String.toData(): Data<Int> = buildData {
        trim().toIntOrNull() ?: error() ?: 0
    }
}

class FloatConverter : Converter<Float> {
    override fun from(data: Float): String = data.toString()
    override fun String.toData(): Data<Float> = buildData {
        trim().toFloatOrNull() ?: error() ?: 0f
    }
}

class ByteArrayConverter : Converter<ByteArray> {
    override fun from(data: ByteArray): String = data.joinToString(",")
    override fun String.toData(): Data<ByteArray> = buildData {
        if (isBlank()) byteArrayOf()
        else split(",")
            .mapNotNull { it.trim().toByteOrNull() ?: error() }
            .toByteArray()
    }
}
