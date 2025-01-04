package com.payam1991gr.kmp.playground.data.math

class VecImpl(vararg x: Float) : Vec() {
    private var data = mutableListOf<Float>()

    init {
        data.addAll(x.toTypedArray())
    }

    override operator fun get(index: Int): Float = data[index]
    override fun toString(): String = data.joinToString(", ", prefix = "[", postfix = "]")
    override fun size(): Int = data.size
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as VecImpl

        return data == other.data
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }
}
