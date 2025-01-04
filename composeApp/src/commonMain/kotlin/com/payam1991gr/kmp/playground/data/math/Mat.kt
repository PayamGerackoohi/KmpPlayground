package com.payam1991gr.kmp.playground.data.math

abstract class Mat {
    companion object {
        fun rix(): Mat = MatImpl()
    }

    abstract fun row(vararg n: Float): Mat
    abstract fun col(vararg n: Float): Mat
    abstract fun dimension(): Pair<Int, Int>
    abstract operator fun get(index: Int): List<Float>
    abstract operator fun times(v: Vec): Mat
}
