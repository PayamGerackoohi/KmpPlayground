package com.payam1991gr.kmp.playground.data.string

import kotlin.random.Random
import kotlin.random.nextULong

class RandomULongGeneratorImpl : RandomULongGenerator {
    override fun randomNumber(): String = Random.nextULong().toString()
}
