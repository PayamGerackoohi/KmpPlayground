package com.payam1991gr.kmp.playground.data.math

abstract class Vec {
    companion object {
        fun tor(vararg x: Float): Vec = VecImpl(*x)
    }

    abstract operator fun get(index: Int): Float
    abstract fun size(): Int
}
