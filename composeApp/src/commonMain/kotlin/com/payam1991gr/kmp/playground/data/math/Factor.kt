package com.payam1991gr.kmp.playground.data.math

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

data class Factor(val base: String, val power: String? = null) {
    companion object {
        fun parse(factors: String): PersistentList<Factor> = factors
            .split('*')
            .filter { it.trim().isNotEmpty() }
            .map { it.split('^') }
            .map { Factor(it.first(), it.getOrNull(1)) }
            .toPersistentList()
    }
}
