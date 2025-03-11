package com.payam1991gr.kmp.playground.data.math

import kotlin.math.cos
import kotlin.math.sin

class MatImpl : Mat() {
    companion object {
        fun rotationOf(angle: Float): Mat {
            val c = cos(angle)
            val s = sin(angle)
            return MatImpl()
                .row(c, -s)
                .row(s, c)
        }
    }

    private var rows = mutableListOf<MutableList<Float>>()

    override fun row(vararg n: Float) = apply {
        rows.add(n.toMutableList())
    }

    override fun col(vararg n: Float) = apply {
        if (rows.isEmpty()) n.forEach { nn ->
            rows.add(mutableListOf(nn))
        } else rows.forEachIndexed { index, row ->
            row.add(n[index])
        }
    }

    override fun dimension(): Pair<Int, Int> = rows.size to if (rows.isEmpty()) 0 else rows[0].size

    override operator fun get(index: Int): List<Float> = rows[index]

    override operator fun times(v: Vec): Mat {
        val result = MatImpl()
        rows.forEach { row ->
            result.row(row.mapIndexed { i, m -> m * v[i] }.sum())
        }
        return result
    }

    override fun flatten() = if (rows.isEmpty() || rows[0].isEmpty()) Vec.tor()
    else if (!rows.all { it.size == 1 }) throw RuntimeException("Invalid Dimension")
    else Vec.tor(*(rows.map { it.first() }).toFloatArray())

    override fun toString(): String = rows.joinToString("\n") { row ->
        row.joinToString(", ", prefix = "[", postfix = "]")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MatImpl

        return rows == other.rows
    }

    override fun hashCode(): Int {
        return rows.hashCode()
    }
}
